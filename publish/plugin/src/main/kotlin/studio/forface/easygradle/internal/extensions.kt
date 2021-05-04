package studio.forface.easygradle.internal

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.MavenPublishPluginExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

/** Configures the [mavenPublish][org.gradle.api.publish.PublishingExtension] extension */
@Suppress("UnstableApiUsage")
internal fun Project.mavenPublish(configure: MavenPublishPluginExtension.() -> Unit) =
    (this as ExtensionAware).extensions.configure("mavenPublish", configure)

/** Configures the [mavenPublishing][org.gradle.api.publish.PublishingExtension] extension */
@Suppress("UnstableApiUsage")
internal fun Project.mavenPublishing(configure: MavenPublishBaseExtension.() -> Unit) =
    (this as ExtensionAware).extensions.configure("mavenPublish", configure)
