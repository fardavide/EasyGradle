buildscript {

    val kotlinVersion = "1.4-M3"

    repositories {
        maven("https://kotlin.bintray.com/kotlin-eap")
        jcenter()
    }
    dependencies {
        classpath(kotlin("gradle-plugin:$kotlinVersion"))
    }
}

apply(from = "../gradle/repositories.gradle.kts")
