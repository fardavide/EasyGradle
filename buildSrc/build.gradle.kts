plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {
    val android =           "3.6.0-alpha03" // Released: Jun 06, 2019
    val dokka =             "0.10.1"        // Released: Feb 04, 2020

    implementation("com.android.tools.build:gradle:$android")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokka")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
