import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.plugin.use.PluginDependenciesSpec

private object Version {
    const val androidGradlePlugin =     "3.5.0-rc03"    // Updated: Aug 08, 2019
    const val dokkaGradlePlugin =       "0.10.1"        // Updated: Oct 07, 2019
    const val mavenPublishPlugin =      "0.11.1"        // Released: Mar 31, 2020

    const val detekt =                  "1.9.1"         // Updated: May 17, 2020
    const val junit =                   "4.13-beta-3"   // Updated: May 05, 2019
    const val kotlin =                  "1.3.72"        // Updated: Apr 14, 2020
    const val mockk =                   "1.9.3"         // Updated: Mar 25, 2019
    const val serialization =           "0.20.0"        // Updated: Mar 04, 2020
}

// Libraries
val DependencyHandler.`android-gradlePlugin` get() = "com.android.tools.build:gradle:${Version.androidGradlePlugin}"
val DependencyHandler.`dokka-gradlePlugin` get() = "org.jetbrains.dokka:dokka-gradle-plugin:${Version.dokkaGradlePlugin}"
val DependencyHandler.`kotlin-gradlePlugin` get() = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
val DependencyHandler.`serialization-gradlePlugin` get() = "org.jetbrains.kotlin:kotlin-serialization:${Version.kotlin}"
val DependencyHandler.`mavenPublish-gradlePlugin` get() = "com.vanniktech:gradle-maven-publish-plugin:${Version.mavenPublishPlugin}"

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
