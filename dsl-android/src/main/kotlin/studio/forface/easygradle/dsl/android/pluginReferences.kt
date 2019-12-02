@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package studio.forface.easygradle.dsl.android

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

val PluginDependenciesSpec.`kotlin-android` get() =             kotlin("android")
val PluginDependenciesSpec.`kotlin-android-extensions` get() =  kotlin("android.extensions")
