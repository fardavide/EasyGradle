package studio.forface.easygradle.internal

import com.vanniktech.maven.publish.MavenPublishPluginExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension

/** Configures the [publishing][org.gradle.api.publish.PublishingExtension] extension */
@Suppress("UnstableApiUsage")
internal fun Project.publishing(configure: PublishingExtension.() -> Unit): Unit =
    (this as ExtensionAware).extensions.configure("publishing", configure)

/** Configures the [mavenPublish][org.gradle.api.publish.PublishingExtension] extension */
@Suppress("UnstableApiUsage")
internal fun Project.mavenPublish(configure: MavenPublishPluginExtension.() -> Unit): Unit =
    (this as ExtensionAware).extensions.configure("mavenPublish", configure)
