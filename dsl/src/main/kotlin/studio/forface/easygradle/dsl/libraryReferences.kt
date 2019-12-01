@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin

// region Kotlin
// region StdLib
/**
 * Builds the dependency notation for Kotlin-stdlib.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin` get() = kotlin("stdlib", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-stdlib-common.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-common` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-common` get() = kotlin("stdlib-common", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-stdlib-jdk7.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-jdk7` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-jdk7` get() = kotlin("stdlib-jdk7", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-stdlib-jdk8.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-jdk8` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-jdk8` get() = kotlin("stdlib-jdk8", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-stdlib-js.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-js` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-js` get() = kotlin("stdlib-js", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-kapt.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-kapt` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-kapt` get() = kotlin("kapt", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-reflect.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-reflect` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-reflect` get() = kotlin("reflect", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-test.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test` get() = kotlin("test", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-test-common.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test-common` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test-common` get() = kotlin("test-common", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-test-js.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test-js` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test-js` get() = kotlin("test-js", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-test-junit.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test-junit` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test-junit` get() = kotlin("test-junit", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-test-junit5.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test-junit5` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test-junit5` get() = kotlin("test-junit5", version = `kotlin version`)

/**
 * Builds the dependency notation for Kotlin-gradle-plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-gradle-plugin` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-gradle-plugin` get() = kotlin("gradle-plugin", version = `kotlin version`)

var `kotlin version` = defaultKotlinVersion
// endregion

// region Coroutines
/**
 * Builds the dependency notation for Coroutines-android.
 * @see kotlinx
 *
 * You can also use `` `coroutines-android` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-android` get() = kotlinx("coroutines-android", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core` get() = kotlinx("coroutines-core", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-common.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-common` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-common` get() = kotlinx("coroutines-core-common", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-iosx64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-iosx64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-iosx64` get() = kotlinx("coroutines-core-iosx64", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-iosarm64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-iosarm64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-iosarm64` get() = kotlinx("coroutines-core-iosarm64", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-iosarm32.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-iosarm32` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-iosarm32` get() = kotlinx("coroutines-core-iosarm32", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-linuxx64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-linuxx64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-linuxx64` get() = kotlinx("coroutines-core-linuxx64", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-macosx64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-macosx64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-macosx64` get() = kotlinx("coroutines-core-macosx64", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-native.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-native` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-native` get() = kotlinx("coroutines-core-native", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-js.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-js` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-js` get() = kotlinx("coroutines-core-js", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-core-windowsx64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-windowsx64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-windowsx64` get() = kotlinx("coroutines-core-windowsx64", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-jdk8.
 * @see kotlinx
 *
 * You can also use `` `coroutines-jdk8` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-jdk8` get() = kotlinx("coroutines-jdk8", version = `coroutines version`)

/**
 * Builds the dependency notation for Coroutines-test.
 * @see kotlinx
 *
 * You can also use `` `coroutines-test` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-test` get() = kotlinx("coroutines-test", version = `coroutines version`)

var `coroutines version` = defaultCoroutinesVersion
// endregion

// region Serialization
/**
 * Builds the dependency notation for Serialization-runtime.
 * @see kotlinx
 *
 * You can also use `` `serialization` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization` get() = kotlinx("serialization-runtime", version = `serialization version`)

/**
 * Builds the dependency notation for Serialization-runtime-common.
 * @see kotlinx
 *
 * You can also use `` `serialization-common` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization-common` get() = kotlinx("serialization-runtime-common", version = `serialization version`)

/**
 * Builds the dependency notation for Serialization-runtime-js.
 * @see kotlinx
 *
 * You can also use `` `serialization-js` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization-js` get() = kotlinx("serialization-runtime-js", version = `serialization version`)

/**
 * Builds the dependency notation for Serialization-runtime-native.
 * @see kotlinx
 *
 * You can also use `` `serialization-native` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization-native` get() = kotlinx("serialization-runtime-native", version = `serialization version`)

var `serialization version` = defaultSerializationVersion

/**
 * Builds the dependency notation for Serialization-gradle-plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `serialization-gradle-plugin` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization-gradle-plugin` get() = kotlin("serialization", version = `kotlin version`)
// endregion

/**
 * Builds the dependency notation for the named Kotlinx [module] at the given [version].
 *
 * @param module simple name of the Kotlinx module, for example "coroutines".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.kotlinx(module: String, version: String? = null): Any =
        "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" } ?: ""}"

// region Ktor
// TODO

var `ktor version` = defaultKtorVersion
// endregion
// endregion

// region Google

// region Dagger
val DependencyHandler.`dagger` get() =                      dagger()
val DependencyHandler.`dagger-compiler` get() =             dagger("compiler")

/**
 * Builds the dependency notation for the named Dagger.
 * @param module simple name of the Dagger, for example "compiler".
 */
fun DependencyHandler.dagger(module: String? = null, version: String = `dagger version`): Any =
        google("dagger", "dagger${module?.let { "-$module" } ?: ""}", version)

var `dagger version` = defaultDaggerVersion
// endregion

/**
 * Builds the dependency notation for the named Google group at the given [version].
 *
 * @param groupName simple name of the Google group, for example "dagger"
 * @param module simple name of the Google module, for example "dagger-compiler". Default is [groupName]
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.google(groupName: String, module: String = groupName, version: String? = null): Any =
        "com.google.$groupName:$module${version?.let { ":$version" } ?: "" }"
// endregion

// region Square
/**
 * Builds the dependency notation for Kotlin Poet.
 * @see squareup
 *
 * You can also use `` `kotlinPoet` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlinPoet` get() = squareup("", "kotlinpoet", version = `kotlinPoet version`)

/**
 * Builds the dependency notation for Kotlin Poet Metadata Specs.
 * @see squareup
 *
 * You can also use `` `kotlinPoet` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlinPoet-metadata-specs` get() = squareup("", "kotlinpoet-metadata-specs", version = `kotlinPoet version`)


var `kotlinPoet version` = defaultKotlinPoetVersion


/**
 * Builds the dependency notation for the named `com.squareup` group at the given [version].
 *
 * @param groupName simple name of the `com.squareup` group, for example "retrofit2"
 * @param module simple name of the `com.squareup` module, for example "retrofit". Default is [groupName]
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.squareup(groupName: String, module: String = groupName, version: String? = null): Any =
        "com.squareup.$groupName:$module${version?.let { ":$version" } ?: "" }"
// endregion

// region Jake Wharton
/**
 * Builds the dependency notation for the named `com.jakewharton` group at the given [version].
 *
 * @param groupName simple name of the `com.jakewharton` group, for example "retrofit"
 * @param module simple name of the `com.jakewharton` module, for example "butterknife". Default is [groupName]
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.jakeWharton(groupName: String, module: String = groupName, version: String? = null): Any =
        "com.jakewharton.$groupName:$module${version?.let { ":$version" } ?: "" }"
// endregion

// region MockK
/**
 * Builds the dependency notation for MockK.
 * @see mockK
 *
 * You can also use `` `mockk` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`mockk` get() = mockK("mockk", version = `mockK version`)

/**
 * Builds the dependency notation for MockK-android.
 * @see mockK
 *
 * You can also use `` `mockk`-android version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`mockk-android` get() = mockK("mockk-android", version = `mockK version`)


var `mockK version` = defaultMockkVersion


/**
 * Builds the dependency notation for the named MockK [module] at the given [version].
 *
 * @param module simple name of the Mockk module, for example "mockk-android".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.mockK(module: String, version: String? = null): Any =
        "io.mockk:$module${version?.let { ":$version" } ?: ""}"
// endregion

// region 4face
/**
 * Builds the dependency notation for the named 4face group at the given [version].
 *
 * @param groupName simple name of the 4face group, for example "viewStateStore"
 * @param module simple name of the 4face module, for example "viewStateStore-paging". Default is [groupName]
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.forface(groupName: String, module: String = groupName, version: String? = null): Any =
        "studio.forface.$groupName:$module${version?.let { ":$version" } ?: "" }"
// endregion
