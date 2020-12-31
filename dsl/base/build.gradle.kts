plugins {
    `kotlin-dsl`
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.12.0"
}

repositories {
    maven("https://dl.bintray.com/4face/4face")
}

dependencies {
    implementation(gradleApi())
    implementation(kotlin("stdlib"))

    // Test
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("studio.forface:assert4k:0.5.3")
    testImplementation("io.mockk:mockk:1.10.0")
}

object Plugin {
    const val id = "studio.forface.easygradle"
    const val name = "EasyGradle"
    const val version = "3.0.3" // TODO project.version
}

gradlePlugin {
    plugins {
        create(Plugin.id) {
            id = Plugin.id
            version = Plugin.version
            implementationClass = "studio.forface.easygradle.dsl.EasyGradlePlugin"
        }
    }
}

pluginBundle {
    val url = "https://github.com/4face-studi0/EasyGradle"
    website = url
    vcsUrl = url
    description = "Set of APIs for enrich Gradle DSL"
    tags = listOf(
        "Gradle",
        "plugin",
        "dsl",
        "Kotlin",
        "Multiplatform"
    )

    plugins.getByName(Plugin.id).displayName = Plugin.name
}

easyPublish {}
