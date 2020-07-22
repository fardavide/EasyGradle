package studio.forface.easygradle.dokka

import org.gradle.api.Plugin
import org.gradle.api.Project

const val EXTENSION_NAME = "easyDokka"

abstract class EasyDokkaPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val ext = target.extensions.create(EXTENSION_NAME, EasyDokkaExtension::class.java, target)
//        target.publish(ext)
    }
}
