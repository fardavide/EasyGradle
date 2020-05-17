plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {

    api(project(":dsl"))

    implementation(

        project(":common"),

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
