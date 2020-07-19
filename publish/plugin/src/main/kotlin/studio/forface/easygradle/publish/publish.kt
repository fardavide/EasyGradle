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
 * Params for [PublishExtension] can be set:
 * * programmatically
 * * in `gradle.properties`
 * * in Environment variable
 * @see PublishExtension params for names and format
 * Objects and lists will respect the JSON standard format
 *
 *
 * @param artifact Optional [PublishExtension.artifact] for the [PublishExtension], this is useful when we have a stored
 *   common [PublishExtension] for the project and we want to apply it for a single module
 *
 * @param config Optional Lambda previously created by [PublishExtension] for have a base setup for [PublishExtension]
 *
 * @param block Lambda for setup [PublishExtension]
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

    publish(c)
}

internal fun Project.publish(ext: PublishExtension) {
    extra["GROUP"] = ext.group
    extra["POM_ARTIFACT_ID"] = ext.artifact
    extra["VERSION_NAME"] = ext.versionName

    extra["POM_NAME"] = ext.name
    extra["POM_DESCRIPTION"] = ext.description
//    extra["POM_INCEPTION_YEAR"] =

//    extra["POM_url"] =
    extra["POM_SCM_URL"] = ext.scmUrl
    extra["POM_SCM_CONNECTION"] = ext.scmConnection.useIfNotBlank { "scm:git:$it" }
    extra["POM_SCM_DEV_CONNECTION"] = ext.scmDevConnection.useIfNotBlank { "scm:git:$it" }

    extra["RELEASE_SIGNING_ENABLED"] = false // TODO get from c

    ext.lics.firstOrNull()?.let { lic ->
        extra["POM_LICENCE_NAME"] = lic.name
        extra["POM_LICENCE_URL"] = lic.url
        extra["POM_LICENCE_DIST"] = "repo"
    }

    ext.devs.firstOrNull()?.let { dev ->
        extra["POM_DEVELOPER_ID"] = dev.id
        extra["POM_DEVELOPER_NAME"] = dev.name
        extra["POM_DEVELOPER_URL"] = dev.email
    }

    apply(plugin = "com.vanniktech.maven.publish")

    mavenPublish {
        targets.getByName("uploadArchives") {

            releaseRepositoryUrl = ext.buildBintrayUrl()
            repositoryUsername = ext.username
            repositoryPassword = ext.apiKey
        }
    }
}

var PublishExtension.version: Version
    get() = throw UnsupportedOperationException()
    set(value) { versionName = value.versionName }

/** Lambda for build a [PublishExtension] within a [Project] */
typealias PublishConfigBuilder = PublishExtension.(Project) -> Unit

internal fun PublishConfigBuilder?.build(project: Project): PublishExtension =
    PublishExtension(project).apply { this@build?.let { it(project) } }
