plugins {
    `kotlin-dsl`
    `kotlin`
}

repositories {
    google()
    jcenter()
}

dependencies {

    api(project(":dsl"))

    implementation(

        // Android
        `android-gradlePlugin`,

        // Test
        `jUnit`,
        `kotlinTest`,
        `kotlinTestJunit`,
        `mockk`
    )
}
