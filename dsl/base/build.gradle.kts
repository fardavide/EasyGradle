plugins {
    kotlin("jvm")
    `kotlin-dsl`
}

repositories {
    maven("https://dl.bintray.com/4face/4face")
}

dependencies {
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("studio.forface:assert4k:0.3.1")
    testImplementation("io.mockk:mockk:1.10.0")
}
