@file:Suppress("unused")


import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTask

/*
 * A file containing params for Dokka
 * Author: Davide Farella
 */

/**
 * Apply studio.forface.easygradle.dsl.dokka script to the given module
 * It will apply the Dokka plugin and setup relative [DokkaTask]
 *
 *
 * @param config base configuration [DokkaConfig]
 *   Use [DokkaConfig] function for create a base configuration
 * ```
val dokkaConfig = DokkaConfig {
    outputDirectory = ...
    outputFormat = ...
}
dokka(config = dokkaConfig)
 * ```
 *
 *
 * @param block Lambda for setup [DokkaConfig]
 * ```
dokka {
    outputDirectory = ...
    outputFormat = ...
}
 * ```
 *
 * They also can be used together
 * ```
val baseConfig = DokkaConfig {
    outputDirectory = ...
}
dokka(baseConfig) {
    outputFormat = ...
}
 * ```
 */
fun Project.dokka(
    config: DokkaConfig = {},
    block: DokkaConfig = {}
) {
    apply(plugin = "org.jetbrains.dokka")
    tasks.withType<DokkaTask> {
        config(this@dokka)
        block(this@dokka)
    }
}

/**
 * Create a base [DokkaConfig] that can be used later
 * @see dokka
 */
fun DokkaConfig(builder: DokkaConfig) = builder

typealias DokkaConfig = DokkaTask.(Project) -> Unit

/**
 * Set [DokkaTask.outputFormat] using [OutputFormat] enum, instead of a String
 */
var DokkaTask.outputFormatType
    get() = OutputFormat.fromType(outputFormat)
    set(value) {
        outputFormat = value.type
    }

enum class OutputFormat(val type: String) {
    /**
     * minimalistic html format used by default, Java classes are translated to Kotlin
     */
    HTML("html"),

    /**
     * looks like normal Javadoc, Kotlin classes are translated to Java
     */
    JAVADOC("javadoc"),

    /**
     * looks like [HTML], but Kotlin classes are translated to Java
     */
    HTML_AS_JAVA("html-as-java"),

    /**
     * markdown structured as [HTML], Java classes are translated to Kotlin
     * * `gfm` - GitHub flavored markdown
     * * `jekyll` - Jekyll compatible markdown
     */
    MARKDOWN("markdown");

    internal companion object {
        fun fromType(type: String) = values().find { it.type.equals(type, ignoreCase = true) }
            ?: throw IllegalArgumentException("Cannot find OutputFormat for type: $type")
    }
}
