package studio.forface.easygradle.internal

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * This works as a lateinit but show a more detailed exception if the property has not been set.
 * It's type is [String] and will be used for versions for accessors
 *
 * @author Davide Farella
 */
fun lateinit() = object : ReadWriteProperty<Any?, String> {

    private var VERSION: String? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        val propertyName = with(property.name) {
            if (STD_FIELD_NAME_REGEX.matches(this)) this
            else "`$this`"
        }

        return VERSION ?: throw UninitializedPropertyAccessException("""
            |'$propertyName' has not been set, ensure to set it at the start of `buildscript` block of Gradle script.
            |Example:
            |// build.gradle.kts - start of the file
            |buildscript {
            |   initVersions()
            |   ...
            |}
            |
            |// versionsConfig.kt in buildSrc
            |fun initVersions() {
            |   $propertyName = "1.0" // desired version here
            |}
            """.trimMargin("|"))
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        VERSION = value
    }
}

private val STD_FIELD_NAME_REGEX = """[0-9_]*[a-zA-Z]+[a-zA-Z0-9_]*""".toRegex()
