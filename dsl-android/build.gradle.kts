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
    implementation(Lib.dokkaGradlePlugin)

    // Test
    testImplementation(Lib.jUnit)
    testImplementation(Lib.kotlinTest)
    testImplementation(Lib.kotlinTestJunit)
    testImplementation(Lib.mockk)
}

dokka()
publish(artifact = "dsl-android")
