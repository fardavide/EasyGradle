package studio.forface.easygradle.dsl

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class EasyGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {}
}
