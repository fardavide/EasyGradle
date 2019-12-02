@file:Suppress(
        "PackageDirectoryMismatch", // Enable implicit import
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package org.gradle.kotlin.dsl

import org.gradle.plugin.use.PluginDependenciesSpec

val PluginDependenciesSpec.`kotlin-android` get() =             kotlin("android")
val PluginDependenciesSpec.`kotlin-android-extensions` get() =  kotlin("android.extensions")
