import studio.forface.easygradle.dsl.`kotlin version`
import studio.forface.easygradle.dsl.`kotlin-jdk8`
import studio.forface.easygradle.dsl.implementation
import studio.forface.easygradle.dsl.version

plugins {
    kotlin("jvm")
    id("studio.forface.easy-publish") version "0.2.3"
    id("studio.forface.easygradle") version "0.1"
}

easyPublish {}

`kotlin version` = "1.4.10"

dependencies {
    implementation(
        `kotlin-jdk8`
    )
}
