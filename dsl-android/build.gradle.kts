@file:Suppress("RemoveRedundantBackticks")

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

    implementation(

        // Android
        `android-gradlePlugin`,

        // Bintray
        `bintray-gradlePlugin`,

        // Dokka Android
        `dokka-gradlePlugin`,

        // Test
        `jUnit`,
        `kotlinTest`,
        `kotlinTestJunit`,
        `mockk`
    )

    detektPlugins(`detekt-formatting`)
}

dokka()
publish(artifact = "dsl-android")
