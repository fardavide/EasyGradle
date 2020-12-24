@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

@Deprecated("Use function style", ReplaceWith("detekt()"))
val PluginDependenciesSpec.`detekt`
    get() = plugin("io.gitlab.arturbosch.detekt").version(`detekt version`)

@Deprecated(
    "Use function style",
    ReplaceWith("dokka()", "studio.forface.easygradle.dsl.*")
)
val PluginDependenciesSpec.`dokka`
    get() = plugin("org.jetbrains.dokka")

@Deprecated(
    "Use function style",
    ReplaceWith("kotlin(Js)", "studio.forface.easygradle.dsl.*")
)
val PluginDependenciesSpec.`kotlin-js`
    get() = kotlin("js")

@Deprecated(
    "Use function style",
    ReplaceWith("kotlin(Jvm)", "studio.forface.easygradle.dsl.*")
)
val PluginDependenciesSpec.`kotlin-jvm`
    get() = kotlin("jvm")

@Deprecated(
    "Use function style",
    ReplaceWith("kotlin(Kapt)", "studio.forface.easygradle.dsl.*")
)
val PluginDependenciesSpec.`kotlin-kapt`
    get() = kotlin("kapt")

@Deprecated(
    "Use function style",
    ReplaceWith("kotlin(Multiplatform)", "studio.forface.easygradle.dsl.*")
)
val PluginDependenciesSpec.`kotlin-multiplatform`
    get() = kotlin("multiplatform")

@Deprecated(
    "Use function style",
    ReplaceWith("kotlin(Serialization)", "studio.forface.easygradle.dsl.*")
)
val PluginDependenciesSpec.`kotlin-serialization`
    get() = kotlin("plugin.serialization")

@Deprecated(
    "Use function style",
    ReplaceWith("sqlDelight()", "studio.forface.easygradle.dsl.*")
)
val PluginDependenciesSpec.`sqlDelight`
    get() = plugin("com.squareup.sqldelight")

@Deprecated("Use 'id'", ReplaceWith("id(id)"))
fun PluginDependenciesSpec.plugin(id: String): PluginDependencySpec = id(id)
