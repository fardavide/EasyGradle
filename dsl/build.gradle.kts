plugins {
    `kotlin-dsl`
    `kotlin`
    `kotlinx-serialization`
}

repositories {
    google()
    jcenter()
}

dependencies {

    implementation(

        // Bintray
        `mavenPublish-gradlePlugin`,

        // Dokka
        `dokka-gradlePlugin`,

        // Accessors
        `kotlin`,
        `serialization`,

        // Test
        `jUnit`,
        `kotlinTest`,
        `kotlinTestJunit`,
        `mockk`
    )

    detektPlugins(`detekt-formatting`)
}

dokka()
publish(artifact = "dsl")
