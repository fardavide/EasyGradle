@file:Suppress("unused") // Public API

package studio.forface.easygradle.dsl

import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.exclude

/** Configures the [publishing][org.gradle.api.publish.PublishingExtension] extension */
@Suppress("UnstableApiUsage")
fun Project.publishing(configure: PublishingExtension.() -> Unit): Unit =
        (this as ExtensionAware).extensions.configure("publishing", configure)

/** Retrieves the [sourceSets][org.gradle.api.tasks.SourceSetContainer] extension */
val Project.sourceSets: SourceSetContainer get() =
    (this as ExtensionAware).extensions.getByName("sourceSets") as SourceSetContainer

/** @return `true` if [Project] has any Android plugin */
val Project.isAndroid get() =
    plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")

/**
 * @return receiver string ( usually a dependency notation ) with version
 * E.g. `` `kotlin-jvm` version "1.3.50" ``
 *
 * @throws IllegalArgumentException if format is not correct.
 */
infix fun Any.version(version: String): String {
    val string = this.toString()
    val parts = string.split(':')

    require((parts.size == 2 || parts.size == 3)) {
        "Invalid dependency format: '$string'. Expected 'group:artifact' or 'group:artifact:version'"
    }

    return "${parts[0]}:${parts[1]}:$version"
}

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
