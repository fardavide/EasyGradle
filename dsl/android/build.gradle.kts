plugins {
    kotlin("jvm")
    `kotlin-dsl`
}

repositories {
    google()
}

dependencies {
    api(project(":dsl"))

    implementation(gradleApi())
    implementation(kotlin("stdlib"))
    implementation("com.android.tools.build:gradle:4.2.0-alpha04")
}

easyPublish {}
