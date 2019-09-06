@file:Suppress("unused") // Public API

package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.ModuleDependency
import org.gradle.kotlin.dsl.exclude

/**
 * @return receiver string ( usually a dependency notation ) with version
 * E.g. `` `kotlin-jvm` version "1.3.50" ``
 */
infix fun Any.version(version: String) = "$this:$version"

// region exclude utils
fun ModuleDependency.exclude(vararg any: Any) {
    any.forEach {
        when(it) {
            is Group -> exclude(it)
            is RemoteLibrary -> exclude(it)
            is List<*> -> it.forEach { item -> exclude(item!!) }
            else -> throw IllegalArgumentException(it.toString())
        }
    }
}
fun ModuleDependency.exclude(vararg groups: Group) {
    groups.forEach(::exclude)
}
fun ModuleDependency.exclude(group: Group) {
    group.all().forEach(::exclude)
}
fun ModuleDependency.exclude(vararg modules: RemoteLibrary) {
    modules.forEach(::exclude)
}
fun ModuleDependency.exclude(library: RemoteLibrary) {
    exclude(group = library.group, module = library.module)
}
// endregion
