import org.jetbrains.dokka.gradle.DokkaTask

buildscript {

    val kotlinVersion = "1.4.0-rc"
    val dokkaVersion = "0.10.1"

    repositories {
        maven("https://kotlin.bintray.com/kotlin-eap")
        jcenter()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath(kotlin("serialization", kotlinVersion))
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaVersion")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    tasks.getByName<DokkaTask>("dokka") {
        outputFormat = "markdown"
        outputDirectory = File(rootDir.parent, "docs${File.separator}${parent!!.name}${File.separator}$name").path
    }
}

apply(from = "../gradle/repositories.gradle.kts")
