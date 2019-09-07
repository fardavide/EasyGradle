@file:Suppress(
        "unused" // Public APIs
)

package studio.forface.easygradle.dsl

import org.gradle.api.Project
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

var Project.archivesBaseName by property<String?>()

/**
 * @return [ReadWriteProperty] for delegate a variable
 *
 * @see Project.findProperty
 * @see Project.setProperty
 *
 * @param name of the property. If none is specified [KProperty.name] will be used
 */
fun <T> property(name: String? = null) = object : ReadWriteProperty<Project, T?> {
    override fun getValue(thisRef: Project, property: KProperty<*>) = thisRef.findProperty<T?>(name ?: property.name)
    override fun setValue(thisRef: Project, property: KProperty<*>, value: T?) {
        thisRef.setProperty(name ?: property.name, value)
    }
}

/**
 * Get a nullable property of [T]
 * @see org.gradle.api -> [Project.findProperty]
 *
 * Note: Explicit generic declaration might be needed; example: `` findProperty<String?> ``
 */
@Suppress(
        "UNCHECKED_CAST", // Cast is checked by safe operator `as?`
        "EXTENSION_SHADOWED_BY_MEMBER" // Available via explicit generic declaration
)
fun <T> Project.findProperty(name: String) = findProperty(name) as? T
