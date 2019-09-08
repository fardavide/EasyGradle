package studio.forface.easygradle.dsl.internal

import org.gradle.api.Project
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * [ReadWriteProperty] for delegate to variables of Configuration classes
 * @author Davide Farella
 */
abstract class ConfigReadWriteProperty<Scope: Any, PropType: Any>(
        private val project: Project,
        private val default: PropType,
        private val propertyName: String? = null,
        private val propertyPrefix: String = ""
) : ReadWriteProperty<Scope, PropType> {
    private var backValue: PropType? = null
    private val KProperty<*>.actualName get() = propertyName ?: "$propertyPrefix${name}"

    /** @see ReadWriteProperty.getValue */
    override fun getValue(thisRef: Scope, property: KProperty<*>) =
            backValue ?: prop(property) ?: default

    /** @see ReadWriteProperty.setValue */
    override fun setValue(thisRef: Scope, property: KProperty<*>, value: PropType) {
        backValue = value
    }

    private fun prop(property: KProperty<*>): PropType? {
        val stringValue = project.findProperty(property.actualName)?.toString() ?: return null
        @Suppress("UNCHECKED_CAST") // Cast is checked because of safe operator `as?`
        return when(default) {
            is String ->    stringValue as? PropType
            is Boolean ->   stringValue.toBoolean() as? PropType
            is Int ->       stringValue.toIntOrNull() as? PropType
            is List<*> ->   stringValue.toList(property)
            else -> throw IllegalArgumentException("'${default::class.simpleName}' is not a supported type")
        }
    }

    /**
     * @return [PropType] which is a [List]
     *
     * @receiver [String] must be in Json format
     *
     * @param [property] [KProperty] which will be used for match the type;
     * e.g. `` if(property == SomeClass::property) { Json.parseList... } ``
     */
    protected open fun String.toList(property: KProperty<*>): PropType? = throw AssertionError()
}