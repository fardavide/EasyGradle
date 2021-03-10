package studio.forface.easygradle.internal

import org.gradle.api.Project
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * [ReadWriteProperty] for delegate to variables of Configuration classes
 * @author Davide Farella
 */
abstract class ConfigReadWriteProperty<Scope : Any, PropType : Any>(
    private val project: Project,
    private val default: PropType,
    private val propertyName: String? = null,
    private val propertyPrefix: String = "",
    private val envName: String? = null
) : ReadWriteProperty<Scope, PropType> {
    private var backValue: PropType? = null
    internal val KProperty<*>.actualPropertyName get() = propertyName ?: "$propertyPrefix$name"
    internal val KProperty<*>.actualEnvName get() = envName ?: actualPropertyName
        .map { char ->
            when {
                char == ' ' -> "_"
                char == '.' -> "_"
                char.isUpperCase() -> "_${char.toUpperCase()}"
                else -> char.toUpperCase().toString()
            }
        }.joinToString(separator = "")

    /** @see ReadWriteProperty.getValue */
    override fun getValue(thisRef: Scope, property: KProperty<*>) =
        backValue ?: env(property) ?: prop(property) ?: default

    /** @see ReadWriteProperty.setValue */
    override fun setValue(thisRef: Scope, property: KProperty<*>, value: PropType) {
        backValue = value
    }

    private fun prop(property: KProperty<*>): PropType? =
        project.findProperty(property.actualEnvName)?.toPropType(property)
            ?: project.findProperty(property.actualPropertyName)?.toPropType(property)

    private fun env(property: KProperty<*>): PropType? =
        System.getenv(property.actualEnvName)?.toPropType(property)

    private fun Any.toPropType(property: KProperty<*>): PropType? {
        val string = toString()
        @Suppress("UNCHECKED_CAST") // Cast is checked because of safe operator `as?`
        return when (default) {
            is String -> string as? PropType
            is Boolean -> string.toBoolean() as? PropType
            is Int -> string.toIntOrNull() as? PropType
            is List<*> -> string.toList(property)
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
