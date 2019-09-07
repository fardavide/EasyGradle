package studio.forface.easygradle.dsl.android

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaAndroidTask
import studio.forface.easygradle.dsl.DokkaConfig
import studio.forface.easygradle.dsl.DokkaConfigBuilder
import java.io.File

/**
 * Apply studio.forface.easygradle.dsl.dokkaAndroid script to the given module
 *
 * Params for [DokkaConfig] can be set in `gradle.properties`
 * @see DokkaConfig params for names and format
 * Objects and lists will respect the JSON standard format
 *
 *
 * @param config Optional [DokkaConfig] to start from
 *
 * @param baseBlock Optional Lambda previously created by [DokkaConfig] for have a base setup for [DokkaConfig]
 *
 * @param block Lambda for setup [DokkaConfig]
 */
fun Project.dokkaAndroid(
        config: DokkaConfig = DokkaConfig(this),
        baseBlock: DokkaConfigBuilder? = null,
        block: DokkaConfigBuilder = {}
) {
    val project = this
    val validConfig = config.apply {
        baseBlock?.let { this.baseBlock(project) }
        this.block(project)
    }
    dokkaAndroid(validConfig)
}

private fun Project.dokkaAndroid(c: DokkaConfig) {
    apply(plugin = "org.jetbrains.dokka-android")
    tasks.withType(DokkaAndroidTask::class) {
        apiVersion = c.apiVersion.toString()
        jdkVersion = c.jdkVersion
        sourceDirs = c.sourceDirs.map { File(it) }
        outputFormat = c.outputFormat
        outputDirectory = c.outputDirectory
    }
}