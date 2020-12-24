@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import org.gradle.plugin.use.PluginDependenciesSpec
import studio.forface.easygradle.dsl.*

/**
 * Example `android(Application)` or `android(Library)`
 */
fun PluginDependenciesSpec.android(module: AndroidModule) = id("com.android.${module.string}")

sealed class AndroidModule(val string: String)

object Application : AndroidModule("application")
object Library : AndroidModule("library")

object Android : KotlinModule("android")
object AndroidExtensions : KotlinModule("android-extensions")
