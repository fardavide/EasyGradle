@file:Suppress("unused", "PackageDirectoryMismatch", "UnnecessaryAbstractClass")

package studio.forface.easygradle.dsl

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.kotlin(module: String) = kotlin(module)
fun PluginDependenciesSpec.detekt() = id("io.gitlab.arturbosch.detekt")
fun PluginDependenciesSpec.dokka() = id("org.jetbrains.dokka")
fun PluginDependenciesSpec.sqlDelight() = id("com.squareup.sqldelight")

abstract class KotlinPluginModule(internal val string: String)

fun kotlinPluginModule(string: String) = object : KotlinPluginModule(string) {}

val PluginDependenciesSpec.Multiplatform get() = kotlinPluginModule("multiplatform")
val PluginDependenciesSpec.Jvm get() = kotlinPluginModule("jvm")
val PluginDependenciesSpec.Js get() = kotlinPluginModule("js")
val PluginDependenciesSpec.Native get() = kotlinPluginModule("native")
val PluginDependenciesSpec.Serialization get() = kotlinPluginModule("plugin.serialization")
val PluginDependenciesSpec.Kapt get() = kotlinPluginModule("kapt")
