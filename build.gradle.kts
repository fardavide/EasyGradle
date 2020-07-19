//import io.gitlab.arturbosch.detekt.DetektPlugin
//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import java.io.File

//plugins {
//    `getDetekt`
//}

subprojects {

//    // Options for Kotlin
//    tasks.withType<KotlinCompile> {
//        kotlinOptions {
//            jvmTarget = "1.8"
//            freeCompilerArgs = freeCompilerArgs +
//                "-XXLanguage:+NewInference" +
//                "-Xuse-experimental=kotlin.Experimental"
//        }
//    }
//
//    // Configure Detekt
//    apply<DetektPlugin>()
//
//    getDetekt {
//        autoCorrect = true
//        failFast = false // fail build on any finding
//        buildUponDefaultConfig = false // preconfigure defaults
//        config = files("$rootDir/config/getDetekt.yml") // point to your custom config defining rules to run
//
//        reports {
//            html.enabled = true // observe findings in your browser with structure and code snippets
//            xml.enabled = false // checkstyle like format mainly for integrations like Jenkins
//            txt.enabled = false // similar to console output, contains issue signature to manually edit baseline files
//        }
//    }
//
//    dependencies {
//        add("detektPlugins", `getDetekt-formatting`ng`)
//    }
//
//    // Configure Dokka
//    dokka {
//        getOutputFormatType = OutputFormat.MARKDOWN
//        outputDirectory = File(rootDir, "docs${File.separator}${it.name}").path
//    }
//
//    // Configure Publishing
//    publish()
}
