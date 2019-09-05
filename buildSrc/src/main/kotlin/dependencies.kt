@file:Suppress("RemoveRedundantBackticks")

import org.gradle.plugin.use.PluginDependenciesSpec

private object Version {
    const val androidGradlePlugin =   "3.5.0-rc03"    // Updated: Aug 08, 2019
    const val bintrayGradlePlugin =   "1.8.4"         // Updated: Jul 08, 2018
    const val dokkaGradlePlugin =     "0.9.18"        // Updated: Mar 19, 2019
}

object Lib {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.androidGradlePlugin}"
    const val bintrayGradlePlugin = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Version.bintrayGradlePlugin}"
    const val dokkaGradlePlugin = "org.jetbrains.dokka:dokka-gradle-plugin:${Version.dokkaGradlePlugin}"
    const val dokkaAndroidGradlePlugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:${Version.dokkaGradlePlugin}"
}

val PluginDependenciesSpec.`bintray` get() = id("com.jfrog.bintray")