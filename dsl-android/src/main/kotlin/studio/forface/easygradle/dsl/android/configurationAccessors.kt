@file:Suppress("unused")

package studio.forface.easygradle.dsl.android

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidTestImplementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        add("androidTestImplementation", it)
    }
}
