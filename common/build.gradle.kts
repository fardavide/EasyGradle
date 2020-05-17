plugins {
    `kotlin-dsl`
    `kotlin`
}

repositories {
    google()
    jcenter()
}

dependencies {

    implementation(
        `kotlin`,

        // Test
        `kotlinTest`,
        `kotlinTestJunit`
    )

    detektPlugins(`detekt-formatting`)
}