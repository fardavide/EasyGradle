plugins {
    `kotlin-dsl`
    `kotlin`
    // Needed for serialize devs and licenses from gradle.properties
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
        `serialization`
    )

    testImplementation(
        `assert4k`,
        `jUnit`,
        `kotlinTest`,
        `kotlinTestJunit`,
        `mockk`
    )
}
