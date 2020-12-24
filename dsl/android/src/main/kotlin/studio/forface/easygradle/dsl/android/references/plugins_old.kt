@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

@Deprecated(
    "Use standard kotlin function",
    ReplaceWith(
        "kotlin(Android)",
        "studio.forface.easygradle.dsl.*",
        "studio.forface.easygradle.dsl.android.*"
    )
)
val PluginDependenciesSpec.`kotlin-android` get() = kotlin("android")

@Deprecated(
    "Use standard kotlin function",
    ReplaceWith(
        "kotlin(AndroidExtensions)",
        "studio.forface.easygradle.dsl.*",
        "studio.forface.easygradle.dsl.android.*"
    )
)
val PluginDependenciesSpec.`kotlin-android-extensions` get() = kotlin("android.extensions")
