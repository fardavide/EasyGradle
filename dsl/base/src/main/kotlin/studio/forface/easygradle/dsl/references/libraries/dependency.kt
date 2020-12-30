@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.useIfNotNull


/**
 * Build a dependency notation
 * @return [Any]
 *
 * @param group the partial group of the dependency. e.g. `` studio.forface ``
 * @param groupName optional name of the group. e.g. `` viewstatestore `` for `studio.forface.viewstatestore`
 * @param module module of the dependency, if not set [groupName] will be used. e.g. `` dagger `` for
 *  `com.google.dagger`
 * @param moduleSuffix optional suffix for the [module]. e.g. `` ktx `` for `core-ktx`
 * @param version optional version for the dependency
 *
 * @throws [IllegalArgumentException] if none of [groupName] and [module] is set
 */
fun dependency(
    group: String,
    groupName: String? = null,
    module: String? = null,
    moduleSuffix: String? = null,
    version: String? = null
): Any {
    val safeModule = module ?: groupName ?: throw IllegalArgumentException("One of 'groupName' or 'module' is required")

    return group +
        groupName.useIfNotNull { ".$it" } +
        ":" +
        safeModule +
        moduleSuffix.useIfNotNull { "-$it" } +
        version.useIfNotNull { ":$it" }
}
