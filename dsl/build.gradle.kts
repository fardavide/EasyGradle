buildscript {

    val kotlinVersion = "1.4-M3"

    repositories {
        maven("https://kotlin.bintray.com/kotlin-eap")
        maven("https://plugins.gradle.org/m2/")
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin:$kotlinVersion"))
        classpath("gradle.plugin.EasyGradle-publish:plugin:0.2.1")
    }
}

subprojects {
    apply(plugin = "studio.forface.easy-publish")
}

apply(from = "../gradle/repositories.gradle.kts")
