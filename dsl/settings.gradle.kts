pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "DSL+"

includeBuild("../publish")

include("base")
include("android")

project(":base").name = "dsl"
project(":android").name = "dsl-android"
