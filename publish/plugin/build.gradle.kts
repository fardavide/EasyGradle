plugins {
    `kotlin-dsl`
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.11.0"
    kotlin("plugin.serialization")
}

repositories {
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://kotlin.bintray.com/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlin-dev")
    jcenter()
}

dependencies {
    implementation("studio.forface.easygradle:dsl:2.0")
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:1.0-M1-1.4.0-rc-218")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.12.0")

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
