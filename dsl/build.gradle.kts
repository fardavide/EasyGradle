buildscript {

    val kotlinVersion = "1.4.0-rc"
    val easyPublishVersion = "0.2.3"

    repositories {
        maven("https://kotlin.bintray.com/kotlin-eap")
        maven("https://plugins.gradle.org/m2/")
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin:$kotlinVersion"))
        classpath("gradle.plugin.EasyPublish:plugin:$easyPublishVersion")
    }
}

subprojects {
    apply(plugin = "studio.forface.easy-publish")
}

apply(from = "../gradle/repositories.gradle.kts")
