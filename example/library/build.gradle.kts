import org.gradle.kotlin.dsl.kotlin
import studio.forface.easygradle.dsl.*

plugins {
    kotlin("jvm")
    id("studio.forface.easy-publish") version "0.2.3"
    id("studio.forface.easygradle") version "3.0"
}

easyPublish {}

`kotlin version` = "1.4.21"

dependencies {
    implementation(
        `coroutines-jdk8`
    )
}
