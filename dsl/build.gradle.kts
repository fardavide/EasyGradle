plugins {
    `kotlin-dsl`
    id("kotlin")
    id("kotlinx-serialization")
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
    implementation(Lib.kotlin)
    implementation(Lib.serialization)

    // Test
    testImplementation(Lib.jUnit)
    testImplementation(Lib.kotlinTest)
    testImplementation(Lib.kotlinTestJunit)
    testImplementation(Lib.mockk)
}

dokka()
publish(artifact = "dsl")
