plugins {
    `kotlin-dsl`
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.11.0"
}

repositories {
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://kotlin.bintray.com/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlin-dev")
    jcenter()
}

dependencies {
    implementation(files("../../dsl/base/build/libs/dsl.jar"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:0.10.1")
}

object Plugin {
    const val id = "studio.forface.easy-dokka"
    const val name = "EasyDokka"
    const val version = "0.1"
}

gradlePlugin {
    plugins {
        create(Plugin.id) {
            id = Plugin.id
            version = Plugin.version
            implementationClass = "studio.forface.easygradle.dokka.EasyDokkaPlugin"
        }
    }
}

pluginBundle {
    val url = "https://github.com/4face-studi0/EasyGradle"
    website = url
    vcsUrl = url
    description = "Gradle plugin for easy Dokka setup"
    tags = listOf(
        "Gradle",
        "plugin",
        "Dokka",
        "Kotlin",
        "Android",
        "Multiplatform"
    )

    plugins.getByName(Plugin.id).displayName = Plugin.name
}
