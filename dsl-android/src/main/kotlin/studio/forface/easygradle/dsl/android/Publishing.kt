@file:Suppress(
        "MemberVisibilityCanBePrivate", "unused" // Public APIs
)

package studio.forface.easygradle.dsl.android

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.get
import studio.forface.easygradle.dsl.PublishConfig
import studio.forface.easygradle.dsl.PublishConfigBuilder
import studio.forface.easygradle.dsl.internal.PublicationsBundle
import studio.forface.easygradle.dsl.internal._publish
import studio.forface.easygradle.dsl.publishConfig

/**
 * Apply publish script to the given module
 *
 * Params for [PublishConfig] can be set in `gradle.properties`
 * @see PublishConfig params for names and format
 * Objects and lists will respect the JSON standard format
 *
 *
 * @param artifact Optional [PublishConfig.artifact] for the [PublishConfig], this is useful when we have a stored
 * common [PublishConfig] for the project and we want to apply it for a single module
 *
 * @param baseBlock Optional Lambda previously created by [publishConfig] for have a base setup for [PublishConfig]
 *
 * @param block Lambda for setup [PublishConfig]
 */
fun Project.publishAndroid(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    _publish(baseBlock, artifact, block) {
        PublicationsBundle(android.sourceSets["main"].java.srcDirs)
    }
}

private val Project.android get() = (this as ExtensionAware).extensions["android"] as LibraryExtension
