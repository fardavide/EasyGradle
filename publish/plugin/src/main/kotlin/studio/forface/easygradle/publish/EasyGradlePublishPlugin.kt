package studio.forface.easygradle.publish

import org.gradle.api.Plugin
import org.gradle.api.Project

const val EXTENSION_NAME = "publish"

abstract class EasyGradlePublishPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val ext = target.extensions.create(EXTENSION_NAME, PublishExtension::class.java, target)
        target.publish(ext)
    }
}
