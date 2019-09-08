package studio.forface.easygradle.dsl.android

import com.android.build.api.dsl.extension.AndroidExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTask
import studio.forface.easygradle.dsl.DokkaConfig
import studio.forface.easygradle.dsl.internal.ConfigReadWriteProperty
import studio.forface.easygradle.dsl.android.AndroidConfig.Type.*
import java.io.File
import kotlin.reflect.KProperty

class AndroidConfig(project: Project) {
    // region Params
    var id: String? =               null
        set(value) {
            field = value
            type = value?.let { Application } ?: Library
        }
    var versionCode                 by project(0)
    var versionName                 by project("")
    var minSdk                      by project(21)
    var targetSdk                   by project(29)
    var compileSdk =                targetSdk
    var type =                      Library

    var multidex                    by project(false)
    var vectorDrawables             by project(true)
    var testInstrumentationRunner   by project("androidx.test.runner.AndroidJUnitRunner")
    // endregion

    // region Delegation
    private operator fun <T: Any> Project.invoke(
            default: T,
            propertyName: String? = null
    ) = object : ConfigReadWriteProperty<AndroidConfig, T>(
            this,
            default,
            propertyName = propertyName,
            propertyPrefix = "android."
    ) {}
    // endregion

    enum class Type { Application, Library }
}

/** Lambda for build a [AndroidConfig] within a [Project] */
typealias AndroidConfigBuilder = AndroidConfig.(Project) -> Unit

private fun Project.applyAndroid(c: AndroidConfig) {
    when(c.type) {
        Application -> apply(plugin= "com.android.application")
        Library -> apply(plugin = "com.android.library")
    }
    apply(plugin= "kotlin-android")
    apply(plugin= "kotlin-android-extensions")
}