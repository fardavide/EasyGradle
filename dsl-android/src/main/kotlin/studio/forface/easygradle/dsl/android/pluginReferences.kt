@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package studio.forface.easygradle.dsl.android

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

/**
 * Applies The `dokka-android` plugin.
 *
 * You can also use `` `dokka-android` version "0.9.18" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`dokka-android` get() = id("org.jetbrains.dokka-android")

/**
 * Applies The `kotlin-android` plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-android` version "1.3.50" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`kotlin-android` get() = kotlin("android")

/**
 * Applies The `kotlin-android-extensions` plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-android-extensions` version "1.3.50" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`kotlin-android-extensions` get() = kotlin("android.extensions")
