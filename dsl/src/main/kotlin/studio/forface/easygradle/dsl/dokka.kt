package studio.forface.easygradle.dsl

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
        config()
        block()
    }
}

/**
 * Create a base [DokkaConfig] that can be used later
 * @see dokka
 */
fun DokkaConfig(builder: DokkaConfig) = builder

typealias DokkaConfig = DokkaTask.() -> Unit
