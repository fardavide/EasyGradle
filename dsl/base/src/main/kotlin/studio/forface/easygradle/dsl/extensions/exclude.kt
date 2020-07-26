@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.exclude
import studio.forface.easygradle.internal.minus

/**
 * Wildcard for build an exclusion rule rule
 * Example ``  implementation(...) exclude kotlinx(`any`)  ``
 */
@Suppress("unused") // scoped to `DependencyHandler`
val DependencyHandler.`any` get() = ANY

/**
 * Apply an exclusion rules for given [dependency] to receiver dependency notation.
 * Example: ``  `mockk-android` exclude `mockk`  ``
 */
infix fun Any.exclude(dependency: Any): Any =
    if (this is ExcludingDependency) ExcludingDependency(notation, listOf(excludeNotations, dependency))
    else ExcludingDependency(this, listOf(dependency))

/**
 * Concatenate exclusion rules
 * @see exclude
 */
infix fun Any.and(other: Any): Any {
    require(this is ExcludingDependency) { "'exclude' should be called before 'and'" }
    return if (other is ExcludingDependency)
        ExcludingDependency(
            notation.toString() + other.notation,
            excludeNotations + other.excludeNotations
        )
    else ExcludingDependency(notation, excludeNotations + other)
}

/**
 * Apply a set of [dependencies] to exclude from receiver [Dependency]
 * @return [Dependency]
 */
fun Dependency.exclude(vararg dependencies: Any): Dependency {
    dependencies.forEach {
        when (it) {
            is String -> exclude(dependency = it)
            is List<*> -> it.forEach { item -> exclude(item!!) }
            else -> throw IllegalArgumentException("Impossible to exclude the following: $it")
        }
    }
    return this
}

/**
 * Apply a [dependency] to exclude from receiver [Dependency]
 * @return [Dependency]
 */
infix fun Dependency.exclude(dependency: Any): Dependency {
    val (group, module) = RemoteDependencyParts.from(dependency.excluding())
    (this as ModuleDependency).exclude(group, module)
    return this
}

internal fun Any.excluding(): String {
    require(this is String) { "Cannot parse dependency of type: ${this::class.simpleName}" }
    return this - "-$ANY" - ".$ANY" - ANY
}

internal data class RemoteDependencyParts(val group: String, val module: String?, val version: String?) {
    companion object {
        fun from(string: String): RemoteDependencyParts {
            val parts = string.split(':')
            require(parts.isNotEmpty() && parts.size <= DEPENDENCY_PARTS) {
                "Invalid dependency format: '$string'. Expected 'group:artifact' or 'group:artifact:version'"
            }
            return RemoteDependencyParts(
                parts[0],
                parts.getOrNull(1)?.takeIf { it.isNotBlank() },
                parts.getOrNull(2)?.takeIf { it.isNotBlank() }
            )
        }
        private const val DEPENDENCY_PARTS = 3
    }
}

internal data class ExcludingDependency(val notation: Any, val excludeNotations: List<Any>)

internal const val ANY = "\$any$"
