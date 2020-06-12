@file:Suppress("PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

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
