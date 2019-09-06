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
}

publish(baseBlock = defaultPublishConfig, artifact = "dsl")
