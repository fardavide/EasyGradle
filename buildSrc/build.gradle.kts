plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

allprojects {

    val android =   "3.6.0-alpha03" // Updated: Jun 06, 2019
    val bintray =   "1.8.4"         // Updated: Jul 08, 2018
    val dokka =     "0.9.18"        // Updated: Mar 19, 2019

    ext {
        // versions
        set("androidGradlePlugin_version", android)
        set("bintrayGradlePlugin_version", bintray)
        set("dokkaGradlePlugin_version", dokka)

        // libs
        set("androidGradlePlugin", "com.android.tools.build:gradle:$android")
        set("bintrayGradlePlugin", "com.jfrog.bintray.gradle:gradle-bintray-plugin:$bintray")
        set("dokkaGradlePlugin", "org.jetbrains.dokka:dokka-gradle-plugin:$dokka")
        set("dokkaAndroidGradlePlugin", "org.jetbrains.dokka:dokka-android-gradle-plugin:$dokka")
    }
}

dependencies {
    with(extra) {
        implementation(get("androidGradlePlugin")!!)
        implementation(get("bintrayGradlePlugin")!!)
        implementation(get("dokkaGradlePlugin")!!)
        implementation(get("dokkaAndroidGradlePlugin")!!)
    }
}
