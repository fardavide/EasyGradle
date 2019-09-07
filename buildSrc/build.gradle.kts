plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

buildscript {

    val android =           "3.6.0-alpha03" // Updated: Jun 06, 2019
    val bintray =           "1.8.4"         // Updated: Jul 08, 2018
    val dokka =             "0.9.18"        // Updated: Mar 19, 2019
    val kotlin =            "1.3.50"        // Updated:
    val serialization =     "0.12.0"        // Updated: Aug 23, 2019

    with(extra) {
        // versions
        set("androidGradlePlugin_version", android)
        set("bintrayGradlePlugin_version", bintray)
        set("dokkaGradlePlugin_version", dokka)
        set("serialization_version", serialization)

        // libs
        set("androidGradlePlugin", "com.android.tools.build:gradle:$android")
        set("bintrayGradlePlugin", "com.jfrog.bintray.gradle:gradle-bintray-plugin:$bintray")
        set("dokkaGradlePlugin", "org.jetbrains.dokka:dokka-gradle-plugin:$dokka")
        set("dokkaAndroidGradlePlugin", "org.jetbrains.dokka:dokka-android-gradle-plugin:$dokka")
        set("serialization", "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization")
        set("serializationGradlePlugin", "org.jetbrains.kotlin:kotlin-serialization:$kotlin")
    }

    dependencies {
        classpath(extra.get("serializationGradlePlugin")!!)
    }
}

dependencies {
    with(extra) {
        implementation(get("androidGradlePlugin")!!)
        implementation(get("bintrayGradlePlugin")!!)
        implementation(get("dokkaGradlePlugin")!!)
        implementation(get("dokkaAndroidGradlePlugin")!!)
        implementation(get("serialization")!!)
    }
}

apply(plugin = "kotlinx-serialization")