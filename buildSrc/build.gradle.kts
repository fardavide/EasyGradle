plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

buildscript {
    val kotlin =            "1.3.72"        // Released: Apr 14, 2020

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin")
    }
}

dependencies {
    val android =           "3.6.0-alpha03" // Released: Jun 06, 2019
    val bintray =           "1.8.4"         // Released: Jul 08, 2018
    val dokka =             "0.10.1"        // Released: Feb 04, 2020
    val serialization =     "0.20.0"        // Released: Mar 04, 2020

    implementation("com.android.tools.build:gradle:$android")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:$bintray")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokka")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

apply(plugin = "kotlinx-serialization")
