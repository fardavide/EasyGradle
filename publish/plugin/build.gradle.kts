import studio.forface.easygradle.dsl.*

buildscript {
    initVersions()
}

plugins {
    `kotlin-dsl`
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(
        files("../../dsl/base/build/libs/dsl.jar"),
        `kotlin-jdk8`,
        `serialization`,
        "com.vanniktech:gradle-maven-publish-plugin:0.12.0"
    )
    testImplementation(
        `kotlin-test`,
        `kotlin-test-junit`,
        `mockk`
    )
}
