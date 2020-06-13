@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.exclude

/**
 * A [String] classifier for build a dependency for [exclude] rules
 * E.g. `` implementation(...) exclude kotlinx(`any`) ``
 */
@Suppress("unused") // scoped to `DependencyHandler`
val DependencyHandler.`any` get() = ANY

fun Dependency.exclude(vararg dependencies: Any) {
    dependencies.forEach {
        when (it) {
            is String -> exclude(dependency = it)
            is List<*> -> it.forEach { item -> exclude(item!!) }
            else -> throw IllegalArgumentException("Impossible to exclude the following: $it")
        }
    }
}

fun Dependency.exclude(dependency: String) {
    val (group, module) = RemoteDependencyParts.from(dependency)
    (this as ModuleDependency).exclude(group, module.takeIf { it != ANY })
}

internal data class RemoteDependencyParts(val group: String, val module: String?, val version: String?) {
    companion object {
        fun from(string: String): RemoteDependencyParts {
            val parts = string.split(':')
            require(parts.isNotEmpty() && parts.size <= DEPENDENCY_PARTS) {
                "Invalid dependency format: '$string'. Expected 'group:artifact' or 'group:artifact:version'"
            }
            return RemoteDependencyParts(parts[0], parts.getOrNull(1), parts.getOrNull(2))
        }
        private const val DEPENDENCY_PARTS = 3
    }
}

private const val ANY = "\$any$"
