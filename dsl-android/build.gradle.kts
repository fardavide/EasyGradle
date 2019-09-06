plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {

    // base dsl module
    api(project(":dsl"))

    // Android
    implementation(Lib.androidGradlePlugin)

    // Bintray
    implementation(Lib.bintrayGradlePlugin)

    // Dokka Android
    implementation(Lib.dokkaAndroidGradlePlugin)
}

publish(baseBlock = defaultPublishConfig, artifact = "dsl-android")
