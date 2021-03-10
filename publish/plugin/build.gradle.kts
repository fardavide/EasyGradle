plugins {
    `kotlin-dsl`
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.11.0"
    kotlin("plugin.serialization")
}

dependencies {
    implementation("studio.forface.easygradle:dsl:2.8") // Nov 26, 2020
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0") // Feb 19, 2021
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.13.0")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("io.mockk:mockk:1.10.6") // Feb 12, 2021
}

object Plugin {
    const val id = "studio.forface.easy-publish"
    const val name = "Easy-publish"
    const val version = "0.2.4"
}

gradlePlugin {
    plugins {
        create(Plugin.id) {
            id = Plugin.id
            version = Plugin.version
            implementationClass = "studio.forface.easygradle.publish.EasyPublishPlugin"
        }
    }
}

pluginBundle {
    val url = "https://github.com/4face-studi0/EasyGradle"
    website = url
    vcsUrl = url
    description = "Gradle plugin for publish on Bintray"
    tags = listOf(
        "Gradle",
        "plugin",
        "publish",
        "Maven",
        "Bintray",
        "Kotlin",
        "Android",
        "Multiplatform"
    )

    plugins.getByName(Plugin.id).displayName = Plugin.name
}
