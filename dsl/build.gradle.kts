plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {

    // Bintray
    implementation(Lib.bintrayGradlePlugin)

    // Dokka
    implementation(Lib.dokkaGradlePlugin)

    // Accessors
    implementation(Lib.serialization)

    // Test
    testImplementation(Lib.jUnit)
    testImplementation(Lib.kotlinTest)
    testImplementation(Lib.kotlinTestJunit)
    testImplementation(Lib.mockk)
}

publish(artifact = "dsl")
