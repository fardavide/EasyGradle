package studio.forface.easygradle.publish

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class EasyGradlePublishPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // noop
    }

    companion object
}
