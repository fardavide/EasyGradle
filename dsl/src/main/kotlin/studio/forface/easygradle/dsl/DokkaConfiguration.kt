@file:Suppress(
        "MemberVisibilityCanBePrivate", "unused" // Public APIs
)
package studio.forface.easygradle.dsl

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTask
import studio.forface.easygradle.internal.ConfigReadWriteProperty
import studio.forface.easygradle.internal.writeOnly
import kotlin.reflect.KProperty

/*
 * A file containing params for Dokka
 * Author: Davide Farella
 */

/**
 * Apply studio.forface.easygradle.dsl.dokka script to the given module
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
fun Project.dokka(
    config: DokkaConfig = DokkaConfig(this),
    baseBlock: DokkaConfigBuilder? = null,
    block: DokkaConfigBuilder = {}
) {
    val project = this
    val validConfig = config.apply {
        baseBlock?.let { this.baseBlock(project) }
        this.block(project)
    }
    dokka(validConfig)
}

/**
 * @return [DokkaConfigBuilder]
 * @param block Lambda for setup [DokkaConfig]
 */
fun dokkaConfig(block: DokkaConfigBuilder): DokkaConfigBuilder = { apply { this.block(it) } }

@Suppress("MagicNumber")
class DokkaConfig(project: Project) {
    // region Params
    @Deprecated("Unused! Replacement not available yet")
    var apiVersion                  by project(0)
    var jdkVersion                  by project(8)
    @Deprecated("Unused! Replacement not available yet")
    var sourceDirs: List<String>    by project(listOf("src/main/kotlin"))
    @Deprecated("Unused! Replacement not available yet")
    var sourceDir: String           by writeOnly<String> { sourceDirs = listOf(it) }
    var outputFormat                by project("html")
    var outputDirectory             by project("doc")
    // endregion

    @UseExperimental(ImplicitReflectionSerializer::class)
    private operator fun <T : Any> Project.invoke(
        default: T,
        propertyName: String? = null
    ) = object : ConfigReadWriteProperty<DokkaConfig, T>(
        this,
        default,
        propertyName = propertyName,
        propertyPrefix = "dokka."
    ) {
        override fun String.toList(property: KProperty<*>): T? {
            @Suppress("UNCHECKED_CAST", "DuplicatedCode")
            return when (property) {
                DokkaConfig::sourceDirs -> Json.parseList<String>(this) as? T?
                else -> throw AssertionError()
            }
        }
    }
}

/** Lambda for build a [DokkaConfig] within a [Project] */
typealias DokkaConfigBuilder = DokkaConfig.(Project) -> Unit

private fun Project.dokka(c: DokkaConfig) {
    apply(plugin = "org.jetbrains.dokka")
    tasks.withType(DokkaTask::class) {
        //        apiVersion = c.apiVersion.toString()
        outputFormat = c.outputFormat
        outputDirectory = c.outputDirectory
        configuration {
            //            sourceRoots = c.sourceDirs.map { File(it) }
            jdkVersion = c.jdkVersion
        }
    }
}
