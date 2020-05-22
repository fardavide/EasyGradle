import io.gitlab.arturbosch.detekt.DetektPlugin

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath(`kotlin-gradlePlugin`)
        classpath(`serialization-gradlePlugin`)
    }
}

plugins {
    `detekt`
}

subprojects {

    // Options for Kotlin
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = freeCompilerArgs +
                "-XXLanguage:+NewInference" +
                "-Xuse-experimental=kotlin.Experimental"
        }
    }

    // Configure Detekt
    apply<DetektPlugin>()

    detekt {
        autoCorrect = true
        failFast = false // fail build on any finding
        buildUponDefaultConfig = true // preconfigure defaults
        config = files("$rootDir/config/detekt.yml") // point to your custom config defining rules to run
//        baseline = file("$rootDir/config/baseline.xml") // a way of suppressing issues before introducing detekt

        reports {
            html.enabled = true // observe findings in your browser with structure and code snippets
            xml.enabled = false // checkstyle like format mainly for integrations like Jenkins
            txt.enabled = false // similar to the console output, contains issue signature to manually edit baseline files
        }
    }
}
