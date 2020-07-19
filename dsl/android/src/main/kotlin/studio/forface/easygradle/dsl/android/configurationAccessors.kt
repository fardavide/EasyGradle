@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidTestImplementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        addX("androidTestImplementation", it)
    }
}
