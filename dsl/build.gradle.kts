import studio.forface.easygradle.dsl.publish

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {

    // buildSrc utils
    api(project(":buildSrc"))

    // Bintray
    implementation(Lib.bintrayGradlePlugin)

    // Dokka
    implementation(Lib.dokkaGradlePlugin)
}

publish(baseBlock = defaultPublishConfig, artifact = "dsl")
