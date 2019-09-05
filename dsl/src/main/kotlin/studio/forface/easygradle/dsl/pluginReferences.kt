@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package studio.forface.easygradle.dsl

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

/**
 * Applies The `dokka` plugin.
 *
 * You can also use `` `dokka` version "0.9.18" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`dokka` get() = id("org.jetbrains.dokka")

/**
 * Applies The `kotlin-js` plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-js` version "1.3.50" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`kotlin-js` get() = kotlin("js")

/**
 * Applies The `kotlin-jvm` plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-jvm` version "1.3.50" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`kotlin-jvm` get() = kotlin("jvm")

/**
 * Applies The `kotlin-kapt` plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-kapt` version "1.3.50" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`kotlin-kapt` get() = kotlin("kapt")

/**
 * Applies The `kotlin-multiplatform` plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-multiplatform` version "1.3.50" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`kotlin-multiplatform` get() = kotlin("multiplatform")

/**
 * Applies The `kotlin-serialization` plugin.
 * @see org.gradle.kotlin.dsl.kotlin
 *
 * You can also use `` `kotlin-serialization` version "1.3.50" `` if you want to use a different version.
 */
val PluginDependenciesSpec.`kotlin-serialization` get() = kotlin("plugin.serialization")
