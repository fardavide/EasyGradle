@file:Suppress("unused")
package studio.forface.easygradle.publish

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.extra
import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.mavenPublish
import studio.forface.easygradle.internal.useIfNotBlank

/**
 * Apply publish script to the receiver [Project]
 *
 * Params for [PublishConfig] can be set:
 * * programmatically
 * * in `gradle.properties`
 * * in Environment variable
 * @see PublishConfig params for names and format
 * Objects and lists will respect the JSON standard format
 *
 *
 * @param artifact Optional [PublishConfig.artifact] for the [PublishConfig], this is useful when we have a stored
 *   common [PublishConfig] for the project and we want to apply it for a single module
 *
 * @param config Optional Lambda previously created by [PublishConfig] for have a base setup for [PublishConfig]
 *
 * @param block Lambda for setup [PublishConfig]
 */
fun Project.publish(
    config: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    val c = config.build(this).apply {
        artifact?.let { this.artifact = it }
        block(project)
        validate()
    }

    extra["GROUP"] = c.group
    extra["POM_ARTIFACT_ID"] = c.artifact
    extra["VERSION_NAME"] = c.versionName

    extra["POM_NAME"] = c.name
    extra["POM_DESCRIPTION"] = c.description
//    extra["POM_INCEPTION_YEAR"] =

//    extra["POM_url"] =
    extra["POM_SCM_URL"] = c.scmUrl
    extra["POM_SCM_CONNECTION"] = c.scmConnection.useIfNotBlank { "scm:git:$it" }
    extra["POM_SCM_DEV_CONNECTION"] = c.scmDevConnection.useIfNotBlank { "scm:git:$it" }

    extra["RELEASE_SIGNING_ENABLED"] = false // TODO get from c

    c.lics.firstOrNull()?.let { lic ->
        extra["POM_LICENCE_NAME"] = lic.name
        extra["POM_LICENCE_URL"] = lic.url
        extra["POM_LICENCE_DIST"] = "repo"
    }

    c.devs.firstOrNull()?.let { dev ->
        extra["POM_DEVELOPER_ID"] = dev.id
        extra["POM_DEVELOPER_NAME"] = dev.name
        extra["POM_DEVELOPER_URL"] = dev.email
    }

    apply(plugin = "com.vanniktech.maven.publish")

    mavenPublish {
        targets.getByName("uploadArchives") {

            releaseRepositoryUrl = c.buildBintrayUrl()
            repositoryUsername = c.username
            repositoryPassword = c.apiKey
        }
    }
}

var PublishConfig.version: Version
    get() = throw UnsupportedOperationException()
    set(value) { versionName = value.versionName }

/** Lambda for build a [PublishConfig] within a [Project] */
typealias PublishConfigBuilder = PublishConfig.(Project) -> Unit

internal fun PublishConfigBuilder?.build(project: Project): PublishConfig =
    PublishConfig(project).apply { this@build?.let { it(project) } }
