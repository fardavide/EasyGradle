import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.plugin.use.PluginDependenciesSpec

private object Version {

    const val detekt =                  "1.9.1"         // Updated: May 17, 2020
}

// Libraries
val DependencyHandler.`detekt-formatting` get() = "io.gitlab.arturbosch.detekt:detekt-formatting:${Version.detekt}"

// Plugins
val PluginDependenciesSpec.`detekt` get() = id("io.gitlab.arturbosch.detekt").version(Version.detekt)

