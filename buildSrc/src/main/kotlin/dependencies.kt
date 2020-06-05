@file:Suppress("RemoveRedundantBackticks", "ObjectPropertyName", "unused")

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.plugin.use.PluginDependenciesSpec

private object Version {
    const val androidGradlePlugin =     "3.5.0-rc03"    // Updated: Aug 08, 2019
    const val bintrayGradlePlugin =     "1.8.4"         // Updated: Jul 08, 2018
    const val dokkaGradlePlugin =       "0.10.0"        // Updated: Oct 07, 2019

    const val detekt =                  "1.6.0"         // Updated: Feb 26, 2020
    const val junit =                   "4.13-beta-3"   // Updated: May 05, 2019
    const val kotlin =                  "1.3.72"        // Updated: Apr 14, 2020
    const val mockk =                   "1.9.3"         // Updated: Mar 25, 2019
    const val serialization =           "0.20.0"        // Updated: Mar 04, 2020
}

// Libraries
val DependencyHandler.`android-gradlePlugin` get() = "com.android.tools.build:gradle:${Version.androidGradlePlugin}"
val DependencyHandler.`bintray-gradlePlugin` get() = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Version.bintrayGradlePlugin}"
val DependencyHandler.`dokka-gradlePlugin` get() = "org.jetbrains.dokka:dokka-gradle-plugin:${Version.dokkaGradlePlugin}"
val DependencyHandler.`kotlin-gradlePlugin` get() = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
val DependencyHandler.`serialization-gradlePlugin` get() = "org.jetbrains.kotlin:kotlin-serialization:${Version.kotlin}"

val DependencyHandler.`detekt-formatting` get() = "io.gitlab.arturbosch.detekt:detekt-formatting:${Version.detekt}"
val DependencyHandler.`jUnit` get() = "junit:junit:${Version.junit}"
val DependencyHandler.`kotlin` get() = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
val DependencyHandler.`kotlinTest` get() = "org.jetbrains.kotlin:kotlin-test:${Version.kotlin}"
val DependencyHandler.`kotlinTestJunit` get() = "org.jetbrains.kotlin:kotlin-test-junit:${Version.kotlin}"
val DependencyHandler.`mockk` get() = "io.mockk:mockk:${Version.mockk}"
val DependencyHandler.`serialization` get() = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Version.serialization}"

// Plugins
val PluginDependenciesSpec.`detekt` get() = id("io.gitlab.arturbosch.detekt").version(Version.detekt)
val PluginDependenciesSpec.`kotlin` get() = id("kotlin")
val PluginDependenciesSpec.`kotlinx-serialization` get() = id("kotlinx-serialization")

// Utils
fun DependencyHandler.api(vararg dependencyNotations: Any) {
    for (dep in dependencyNotations) add("api", dep)
}
fun DependencyHandler.implementation(vararg dependencyNotations: Any) {
    for (dep in dependencyNotations) add("implementation", dep)
}
fun DependencyHandler.testImplementation(vararg dependencyNotations: Any) {
    for (dep in dependencyNotations) add("testImplementation", dep)
}
