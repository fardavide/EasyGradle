@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.kotlin(kotlinModule: KotlinModule) = kotlin(kotlinModule.string)
fun PluginDependenciesSpec.detekt() = id("io.gitlab.arturbosch.detekt")
fun PluginDependenciesSpec.dokka() = id("org.jetbrains.dokka")
fun PluginDependenciesSpec.sqlDelight() = id("com.squareup.sqldelight")

@Suppress("UnnecessaryAbstractClass")
abstract class KotlinModule(internal val string: String)

object Multiplatform : KotlinModule("multiplatform")
object Jvm : KotlinModule("jvm")
object Js : KotlinModule("js")
object Native : KotlinModule("native")
object Serialization : KotlinModule("plugin.serialization")
object Kapt : KotlinModule("kapt")
