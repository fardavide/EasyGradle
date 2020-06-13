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
        buildUponDefaultConfig = false // preconfigure defaults
        config = files("$rootDir/config/detekt.yml") // point to your custom config defining rules to run

        reports {
            html.enabled = true // observe findings in your browser with structure and code snippets
            xml.enabled = false // checkstyle like format mainly for integrations like Jenkins
            txt.enabled = false // similar to console output, contains issue signature to manually edit baseline files
        }
    }

    dependencies {
        add("detektPlugins", `detekt-formatting`)
    }

    // Configure Dokka
    dokka {
        outputFormatType = OutputFormat.MARKDOWN
    }

    // Configure Publishing
    publish()
}
