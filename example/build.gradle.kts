buildscript {

    val kotlinVersion = "1.4.0-rc"

    repositories {
        maven("https://kotlin.bintray.com/kotlin-eap")
        jcenter()
    }
    dependencies {
        classpath(kotlin("gradle-plugin:$kotlinVersion"))
        classpath(kotlin("serialization:$kotlinVersion"))
    }
}

apply(from = "../gradle/repositories.gradle.kts")
