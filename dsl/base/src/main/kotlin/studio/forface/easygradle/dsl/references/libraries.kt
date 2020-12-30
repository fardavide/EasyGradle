@file:Suppress("unused", "PackageDirectoryMismatch", "UnnecessaryAbstractClass")
package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit
import studio.forface.easygradle.internal.useIfNotNull


var `assert4k version` by lateinit()

fun assert4k() =
    forface(module = "assert4k") version `assert4k version`


var `kotlin version` by lateinit()

fun kotlin(moduleSuffix: String? = null) =
    jetbrains("kotlin", moduleSuffix = moduleSuffix) version `kotlin version`


var `coroutines version` by lateinit()

fun coroutines(moduleSuffix: String? = null) =
    kotlinx("coroutines-$moduleSuffix") version `coroutines version`


var `serialization version` by lateinit()

fun serialization(module: String) =
    kotlinx("serialization-$module") version `serialization version`


var `ktor version` by lateinit()

fun ktorClient(module: String) =
    ktor("client", module)

fun ktorClientServer(module: String) =
    ktor("server", module)

fun ktor(module: String, moduleSuffix: String) =
    dependency("io", groupName = "ktor", module = module, moduleSuffix = moduleSuffix) version `ktor version`


var `assistedInject version` by lateinit()

fun assistedInject(module: String) =
    squareup("inject", module = module) version `assistedInject version`


var `kotlinPoet version` by lateinit()

fun kotlinPoet(moduleSuffix: String? = null) =
    squareup("kotlinpoet", moduleSuffix = moduleSuffix) version `kotlinPoet version`


var `dagger version` by lateinit()

fun dagger(moduleSuffix: String? = null) =
    daggerGroup("dagger", moduleSuffix = moduleSuffix) version `dagger version`


var `detekt version` by lateinit()

fun detekt(moduleSuffix: String) =
    dependency("io.gitlab.arturbosch", groupName = "detekt", moduleSuffix = moduleSuffix) version `detekt version`


var `detekt-code-analysis version` by lateinit()

fun detektCodeAnalysis() =
    dependency("pm.algirdas", "detekt", "codeanalysis") version `detekt-code-analysis version`


var `mockK version` by lateinit()

fun mockK(moduleSuffix: String? = null) =
    dependency("io.mockk", module = "mockk", moduleSuffix = moduleSuffix) version `mockK version`


var `sqlDelight version` by lateinit()

fun sqlDelight(module: String? = null) =
    squareup("sqldelight", module = module) version `sqlDelight version`


// region groups
fun daggerGroup(module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    google("dagger", module = module, moduleSuffix = moduleSuffix, version = version)

fun kotlinx(moduleSuffix: String? = null, version: String? = null) =
    jetbrains("kotlinx", moduleSuffix = moduleSuffix, version = version)
// endregion

// region subgroups
fun forface(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("studio.forface", groupName, module, moduleSuffix, version)

fun google(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.google", groupName, module, moduleSuffix, version)

fun jakeWharton(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.jakewharton", groupName, module, moduleSuffix, version)

fun jetbrains(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("org.jetbrains", groupName, module, moduleSuffix, version)

fun squareup(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.squareup", groupName, module, moduleSuffix, version)
// endregion

// region base
/**
 * Build a dependency notation
 * @return [Any]
 *
 * @param group the partial group of the dependency. e.g. `` studio.forface ``
 * @param groupName optional name of the group. e.g. `` viewstatestore `` for `studio.forface.viewstatestore`
 * @param module module of the dependency, if not set [groupName] will be used. e.g. `` dagger `` for `com.google.dagger`
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
// endregion
