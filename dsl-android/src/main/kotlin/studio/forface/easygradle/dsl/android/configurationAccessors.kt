@file:Suppress(
        "unused" // Public APIs
)

package studio.forface.easygradle.dsl.android

import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.dsl.LocalLibrary
import studio.forface.easygradle.dsl.RemoteLibrary

fun DependencyHandler.androidTestImplementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        when(it) {
            is LocalLibrary -> androidTestImplementation(it)
            is RemoteLibrary -> androidTestImplementation(it)
            else -> add("androidTestImplementation", it)
        }
    }
}