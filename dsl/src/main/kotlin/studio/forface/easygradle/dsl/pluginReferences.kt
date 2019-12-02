@file:Suppress(
        "PackageDirectoryMismatch", // Enable implicit import
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package org.gradle.kotlin.dsl

import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.`dokka` get() =                  plugin("org.jetbrains.dokka")
val PluginDependenciesSpec.`kotlin-js` get() =              kotlin("js")
val PluginDependenciesSpec.`kotlin-jvm` get() =             kotlin("jvm")
val PluginDependenciesSpec.`kotlin-kapt` get() =            kotlin("kapt")
val PluginDependenciesSpec.`kotlin-multiplatform` get() =   kotlin("multiplatform")
val PluginDependenciesSpec.`kotlin-serialization` get() =   kotlin("plugin.serialization")

fun PluginDependenciesSpec.plugin(id: String): PluginDependencySpec = id(id)

