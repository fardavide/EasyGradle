@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import studio.forface.easygradle.dsl.*

val PluginDependenciesSpec.`kotlin-android` get() = kotlin("android")
val PluginDependenciesSpec.`kotlin-android-extensions` get() = kotlin("android.extensions")
