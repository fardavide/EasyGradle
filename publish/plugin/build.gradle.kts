plugins {
    `kotlin-dsl`
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.11.0"
    kotlin("plugin.serialization")
}

repositories {
    maven("https://kotlin.bintray.com/kotlinx")
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("studio.forface.easygradle:dsl:2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.13.0")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("io.mockk:mockk:1.10.0")
}

object Plugin {
    const val id = "studio.forface.easy-publish"
    const val name = "Easy-publish"
    const val version = "0.2.3"
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
