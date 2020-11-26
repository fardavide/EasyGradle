import io.gitlab.arturbosch.detekt.DetektPlugin
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask
import studio.forface.easygradle.publish.EasyPublishPlugin

plugins {
    val kotlinVersion = "1.4.10"
    kotlin("jvm") version kotlinVersion apply false
    id("org.jetbrains.dokka") version kotlinVersion apply false
    id("io.gitlab.arturbosch.detekt") version "1.10.0"
    id("studio.forface.easy-publish") apply false
}

subprojects {
    apply<EasyPublishPlugin>()

    apply<DokkaPlugin>()
    tasks.getByName<DokkaTask>("dokkaJekyll") {
        outputDirectory.set(
            File(rootDir.parent, "docs${File.separator}${parent!!.name}${File.separator}$name")
        )
    }

    apply<DetektPlugin>()
    detekt {
        autoCorrect = true
        failFast = false // fail build on any finding
        buildUponDefaultConfig = false // preconfigure defaults
        config = files("${rootDir.parent}/config/detekt.yml") // point to your custom config defining rules to run

        reports {
            html.enabled = true // observe findings in your browser with structure and code snippets
            xml.enabled = false // checkstyle like format mainly for integrations like Jenkins
            txt.enabled = false // similar to console output, contains issue signature to manually edit baseline files
        }
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.10.0")
    }
}

tasks.create("setupGradleKeys") {
    doLast {
        project.extra["gradle.publish.key"] = System.getenv().getValue("GRADLE_PUBLISH_KEY")
        project.extra["gradle.publish.secret"] = System.getenv().getValue("GRADLE_PUBLISH_SECRET")
    }
}

apply(from = "../gradle/repositories.gradle.kts")
