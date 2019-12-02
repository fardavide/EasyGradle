@file:Suppress(
        "RemoveRedundantBackticks" // Field with backticks without special characters
)
package studio.forface.easygradle.dsl

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
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
    val (group, module) = RemoteDependencyParts.from(string)

    requireNotNull(module) {
        "Invalid dependency format: '$string'. Expected 'group:artifact' or 'group:artifact:version'"
    }

    return "$group:$module:$version"
}

// region exclude utils
infix fun Dependency.exclude(any: Any) {
    (this as ModuleDependency).exclude(any, NoParam)
}
fun ModuleDependency.exclude(vararg any: Any) {
    any.forEach {
        when(it) {
            is NoParam -> { /* no-op */ }
            is String -> exclude(dependency = it)
            is Group -> exclude(it)
            is RemoteLibrary -> exclude(it)
            is List<*> -> it.forEach { item -> exclude(item!!) }
            else -> throw IllegalArgumentException("Impossible to exclude the following: $it")
        }
    }
}
fun ModuleDependency.exclude(dependency: String) {
    val (group, module) = RemoteDependencyParts.from(dependency)
    exclude(group, module)
}
fun ModuleDependency.exclude(group: Group) {
    group.all().forEach(::exclude)
}
fun ModuleDependency.exclude(library: RemoteLibrary) {
    exclude(group = library.group, module = library.module)
}

/**
 * A [String] classifier for build a dependency for [exclude] rules
 * E.g. `` implementation(...) exclude kotlinx(`any`) ``
 */
@Suppress("unused") // scoped to `DependencyHandler`
val DependencyHandler.`any` get() = ANY

private const val ANY = "\$any$"

private data class RemoteDependencyParts(val group: String, val module: String?, val version: String?) {
    companion object {
        fun from(string: String): RemoteDependencyParts {
            val parts = string.split(':')
            require(parts.isNotEmpty() && parts.size <= 3) {
                "Invalid dependency format: '$string'. Expected 'group:artifact' or 'group:artifact:version'"
            }
            return RemoteDependencyParts(parts[0], parts.getOrNull(1), parts.getOrNull(2))
        }
    }
}
private object NoParam
// endregion
