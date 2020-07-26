plugins {
    kotlin("jvm")
    `kotlin-dsl`
}

repositories {
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(kotlin("stdlib"))
    implementation("com.android.tools.build:gradle:4.2.0-alpha04")

    api(project(":dsl"))
}

easyPublish {}
