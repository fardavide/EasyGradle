@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // Field with '-'
        "RemoveRedundantBackticks" // Field with backticks without special characters
)
package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.internal.lateinit
import studio.forface.easygradle.internal.useIfNotNull

// region Kotlin
// region StdLib
val DependencyHandler.`kotlin` get() =                          kotlin("stdlib")
val DependencyHandler.`kotlin-common` get() =                   kotlin("stdlib-common")
val DependencyHandler.`kotlin-jdk7` get() =                     kotlin("stdlib-jdk7")
val DependencyHandler.`kotlin-jdk8` get() =                     kotlin("stdlib-jdk8")
val DependencyHandler.`kotlin-js` get() =                       kotlin("stdlib-js")
val DependencyHandler.`kotlin-kapt` get() =                     kotlin("kapt")
val DependencyHandler.`kotlin-reflect` get() =                  kotlin("reflect")
val DependencyHandler.`kotlin-test` get() =                     kotlin("test")
val DependencyHandler.`kotlin-test-common` get() =              kotlin("test-common")
val DependencyHandler.`kotlin-test-js` get() =                  kotlin("test-js")
val DependencyHandler.`kotlin-test-junit` get() =               kotlin("test-junit")
val DependencyHandler.`kotlin-test-junit5` get() =              kotlin("test-junit5")
val DependencyHandler.`kotlin-gradle-plugin` get() =            kotlin("gradle-plugin")

var `kotlin version` by lateinit()
// endregion

// region Coroutines
val DependencyHandler.`coroutines-android` get() =              coroutines("android")
val DependencyHandler.`coroutines-core` get() =                 coroutines("core")
val DependencyHandler.`coroutines-core-common` get() =          coroutines("core-common")
val DependencyHandler.`coroutines-core-iosx64` get() =          coroutines("core-iosx64")
val DependencyHandler.`coroutines-core-iosarm64` get() =        coroutines("core-iosarm64")
val DependencyHandler.`coroutines-core-iosarm32` get() =        coroutines("core-iosarm32")
val DependencyHandler.`coroutines-core-linuxx64` get() =        coroutines("core-linuxx64")
val DependencyHandler.`coroutines-core-macosx64` get() =        coroutines("core-macosx64")
val DependencyHandler.`coroutines-core-native` get() =          coroutines("core-native")
val DependencyHandler.`coroutines-core-js` get() =              coroutines("core-js")
val DependencyHandler.`coroutines-core-windowsx64` get() =      coroutines("core-windowsx64")
val DependencyHandler.`coroutines-jdk8` get() =                 coroutines("jdk8")
val DependencyHandler.`coroutines-test` get() =                 coroutines("test")

var `coroutines version` by lateinit()
// endregion

// region Serialization
val DependencyHandler.`serialization` get() =                   serialization("runtime")
val DependencyHandler.`serialization-common` get() =            serialization("runtime-common")
val DependencyHandler.`serialization-js` get() =                serialization("runtime-js")
val DependencyHandler.`serialization-native` get() =            serialization("runtime-native")

var `serialization version` by lateinit()

val DependencyHandler.`serialization-gradle-plugin` get() =     kotlin("serialization")
// endregion

// region Ktor
// TODO

var `ktor version` by lateinit()
// endregion
// endregion

// region Google

// region Dagger
val DependencyHandler.`dagger` get() =                          dagger()
val DependencyHandler.`dagger-compiler` get() =                 dagger("compiler")

var `dagger version` by lateinit()
// endregion
// endregion

// region Square
val DependencyHandler.`kotlinPoet` get() =                  squareup("kotlinpoet") version `kotlinPoet version`
val DependencyHandler.`kotlinPoet-metadata-specs` get() =   squareup("kotlinpoet", moduleSuffix = "metadata-specs") version `kotlinPoet version`

var `kotlinPoet version` by lateinit()

val DependencyHandler.`sqlDelight-android-driver` get() =   sqlDelight("android-driver")
val DependencyHandler.`sqlDelight-native-driver` get() =    sqlDelight("native-driver")
val DependencyHandler.`sqlDelight-sqlite-driver` get() =    sqlDelight("sqlite-driver")
val DependencyHandler.`sqlDelight-sqljs-driver` get() =     sqlDelight("sqljs-driver")

val DependencyHandler.`sqlDelight-gradle-plugin` get() =    sqlDelight("gradle-plugin")

var `sqlDelight version` by lateinit()
// endregion

// region Detekt
val DependencyHandler.`detekt-cli` get() =                  detekt("cli")
val DependencyHandler.`detekt-formatting` get() =           detekt("formatting")
val DependencyHandler.`detekt-code-analysis` get() =        dependency("pm.algirdas", "detekt", "codeanalysis") version `detect-code-analysis version`

var `detekt version` by lateinit()
var `detect-code-analysis version` by lateinit()
// endregion

// region MockK
val DependencyHandler.`mockk` get() =                       mockK() version  `mockK version`
val DependencyHandler.`mockk-android` get() =               mockK("android") version  `mockK version`

var `mockK version` by lateinit()
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
fun DependencyHandler.dependency(
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

// region groups
fun DependencyHandler.forface(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("studio.forface", groupName, module, moduleSuffix, version)

fun DependencyHandler.google(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.google", groupName, module, moduleSuffix, version)

fun DependencyHandler.jakeWharton(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.jakewharton", groupName, module, moduleSuffix, version)

fun DependencyHandler.jetbrains(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("org.jetbrains", groupName, module, moduleSuffix, version)

fun DependencyHandler.squareup(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.squareup", groupName, module, moduleSuffix, version)
// endregion

// region subgroups
fun DependencyHandler.kotlinx(moduleSuffix: String? = null, version: String? = null) =
    jetbrains("kotlinx", moduleSuffix = moduleSuffix, version = version)
// endregion

// region modules
fun DependencyHandler.coroutines(moduleSuffix: String? = null, version: String = `coroutines version`) =
    kotlinx("coroutines-$moduleSuffix", version)

fun DependencyHandler.dagger(moduleSuffix: String? = null, version: String = `dagger version`) =
    google("dagger", moduleSuffix = moduleSuffix, version = version)

fun DependencyHandler.detekt(moduleSuffix: String, version: String = `detekt version`) =
    dependency("io.gitlab.arturbosch", groupName = "detekt", moduleSuffix = moduleSuffix, version = version)

fun DependencyHandler.kotlin(moduleSuffix: String? = null, version: String = `kotlin version`) =
    jetbrains("kotlin", moduleSuffix = moduleSuffix, version = version)

fun DependencyHandler.mockK(moduleSuffix: String? = null, version: String = `mockK version`) =
    dependency("io.mockk", module = "mockk", moduleSuffix = moduleSuffix, version = version)

fun DependencyHandler.serialization(moduleSuffix: String? = null, version: String = `serialization version`) =
    kotlinx("serialization-$moduleSuffix", version)

fun DependencyHandler.sqlDelight(module: String? = null, version: String? = `sqlDelight version`) =
    squareup("sqldelight", module = module, version = version)
// endregion
