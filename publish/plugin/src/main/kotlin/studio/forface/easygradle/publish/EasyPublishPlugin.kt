package studio.forface.easygradle.publish

import org.gradle.api.Plugin
import org.gradle.api.Project

const val EXTENSION_NAME = "easyPublish"

abstract class EasyPublishPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val ext = target.extensions.create(EXTENSION_NAME, EasyPublishExtension::class.java, target)
        target.publish(ext)
    }
}
