@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import org.gradle.plugin.use.PluginDependenciesSpec
import studio.forface.easygradle.dsl.*

/**
 * Example `android(Application)` or `android(Library)`
 */
fun PluginDependenciesSpec.android(module: AndroidModule) = id("com.android.${module.string}")

@Suppress("UnnecessaryAbstractClass")
abstract class AndroidModule(val string: String)

fun PluginDependenciesSpec.androidModule(string: String) = object : AndroidModule(string) {}

val PluginDependenciesSpec.Application get() = androidModule("application")
val PluginDependenciesSpec.Library get() = androidModule("library")

val PluginDependenciesSpec.Android get() = kotlinPluginModule("android")
val PluginDependenciesSpec.AndroidExtensions get() = kotlinPluginModule("android-extensions")
