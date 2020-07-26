import org.jetbrains.dokka.gradle.DokkaTask

buildscript {

    val kotlinVersion = "1.4.0-rc"
    val easyPublishVersion = "0.2.3"
    val dokkaVersion = "0.10.1"

    repositories {
        maven("https://kotlin.bintray.com/kotlin-eap")
        maven("https://plugins.gradle.org/m2/")
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin:$kotlinVersion"))
        classpath("gradle.plugin.EasyPublish:plugin:$easyPublishVersion")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaVersion")
    }
}

subprojects {
    apply(plugin = "studio.forface.easy-publish")

    apply(plugin = "org.jetbrains.dokka")
    tasks.getByName<DokkaTask>("dokka") {
        outputFormat = "markdown"
        outputDirectory = File(rootDir.parent, "docs${File.separator}${parent!!.name}${File.separator}$name").path
    }
}

apply(from = "../gradle/repositories.gradle.kts")
