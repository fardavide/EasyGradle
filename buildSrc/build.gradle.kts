plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

buildscript {
    val kotlin =            "1.3.60"        // Updated: Nov 14, 2019

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin")
    }
}

dependencies {
    val android =           "3.6.0-alpha03" // Updated: Jun 06, 2019
    val bintray =           "1.8.4"         // Updated: Jul 08, 2018
    val dokka =             "0.10.0"        // Updated: Oct 07, 2019
    val serialization =     "0.12.0"        // Updated: Aug 23, 2019

    implementation("com.android.tools.build:gradle:$android")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:$bintray")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokka")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization")
}

apply(plugin = "kotlinx-serialization")
