plugins {
    kotlin("jvm")
    `kotlin-dsl`
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
    testImplementation("studio.forface:assert4k:0.3.1")
    testImplementation("io.mockk:mockk:1.10.0")
}

easyPublish {}
