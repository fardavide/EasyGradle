@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin

// region Kotlin
/**
 * Builds the dependency notation for Kotlin-stdlib.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin` get() = kotlin("stdlib")

/**
 * Builds the dependency notation for Kotlin-stdlib-common.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-common` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-common` get() = kotlin("stdlib-common")

/**
 * Builds the dependency notation for Kotlin-stdlib-jdk7.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-jdk7` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-jdk7` get() = kotlin("stdlib-jdk7")

/**
 * Builds the dependency notation for Kotlin-stdlib-jdk8.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-jdk8` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-jdk8` get() = kotlin("stdlib-jdk8")

/**
 * Builds the dependency notation for Kotlin-stdlib-js.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-js` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-js` get() = kotlin("stdlib-js")

/**
 * Builds the dependency notation for Kotlin-kapt.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-kapt` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-kapt` get() = kotlin("kapt")

/**
 * Builds the dependency notation for Kotlin-reflect.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-reflect` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-reflect` get() = kotlin("reflect")

/**
 * Builds the dependency notation for Kotlin-test.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test` get() = kotlin("test")

/**
 * Builds the dependency notation for Kotlin-test-common.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test-common` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test-common` get() = kotlin("test-common")

/**
 * Builds the dependency notation for Kotlin-test-js.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test-js` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test-js` get() = kotlin("test-js")

/**
 * Builds the dependency notation for Kotlin-test-junit.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-test-junit` version "1.3.50" `` if you want to use an explicit version.
 */
val DependencyHandler.`kotlin-test-junit` get() = kotlin("test-junit")
// endregion

// region Coroutines
/**
 * Builds the dependency notation for Coroutines-android.
 * @see kotlinx
 *
 * You can also use `` `coroutines-android` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-android` get() = kotlinx("coroutines-android")

/**
 * Builds the dependency notation for Coroutines-core.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core` get() = kotlinx("coroutines-core")

/**
 * Builds the dependency notation for Coroutines-core-common.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-common` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-common` get() = kotlinx("coroutines-core-common")

/**
 * Builds the dependency notation for Coroutines-core-iosx64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-iosx64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-iosx64` get() = kotlinx("coroutines-core-iosx64")

/**
 * Builds the dependency notation for Coroutines-core-iosarm64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-iosarm64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-iosarm64` get() = kotlinx("coroutines-core-iosarm64")

/**
 * Builds the dependency notation for Coroutines-core-iosarm32.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-iosarm32` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-iosarm32` get() = kotlinx("coroutines-core-iosarm32")

/**
 * Builds the dependency notation for Coroutines-core-linuxx64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-linuxx64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-linuxx64` get() = kotlinx("coroutines-core-linuxx64")

/**
 * Builds the dependency notation for Coroutines-core-macosx64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-macosx64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-macosx64` get() = kotlinx("coroutines-core-macosx64")

/**
 * Builds the dependency notation for Coroutines-core-native.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-native` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-native` get() = kotlinx("coroutines-core-native")

/**
 * Builds the dependency notation for Coroutines-core-js.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-js` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-js` get() = kotlinx("coroutines-core-js")

/**
 * Builds the dependency notation for Coroutines-core-windowsx64.
 * @see kotlinx
 *
 * You can also use `` `coroutines-core-windowsx64` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-core-windowsx64` get() = kotlinx("coroutines-core-windowsx64")

/**
 * Builds the dependency notation for Coroutines-jdk8.
 * @see kotlinx
 *
 * You can also use `` `coroutines-jdk8` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-jdk8` get() = kotlinx("coroutines-jdk8")

/**
 * Builds the dependency notation for Coroutines-test.
 * @see kotlinx
 *
 * You can also use `` `coroutines-test` version "1.3.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`coroutines-test` get() = kotlinx("coroutines-test")
// endregion

// region Serialization
/**
 * Builds the dependency notation for Serialization-runtime.
 * @see kotlinx
 *
 * You can also use `` `serialization` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization` get() = kotlinx("serialization-runtime")

/**
 * Builds the dependency notation for Serialization-runtime-common.
 * @see kotlinx
 *
 * You can also use `` `serialization-common` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization-common` get() = kotlinx("serialization-runtime-common")

/**
 * Builds the dependency notation for Serialization-runtime-js.
 * @see kotlinx
 *
 * You can also use `` `serialization-js` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization-js` get() = kotlinx("serialization-runtime-js")

/**
 * Builds the dependency notation for Serialization-runtime-native.
 * @see kotlinx
 *
 * You can also use `` `serialization-native` version "0.12.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`serialization-native` get() = kotlinx("serialization-runtime-native")
// endregion

// region Ktor
// TODO
// endregion

/**
 * Builds the dependency notation for the named Kotlinx [module] at the given [version].
 *
 * @param module simple name of the Kotlinx module, for example "coroutines".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.kotlinx(module: String, version: String? = null): Any =
        "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" } ?: ""}"