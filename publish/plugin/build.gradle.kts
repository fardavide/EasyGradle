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
    implementation(files("../../dsl/base/build/libs/dsl.jar"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0-1.4-M3")
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.12.0")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("io.mockk:mockk:1.10.0")
}

gradlePlugin {
    plugins {
        create("publish") {
            id = "studio.forface.easy-publish"
            implementationClass = "studio.forface.easygradle.publish.EasyGradlePublishPlugin"
        }
    }
}
