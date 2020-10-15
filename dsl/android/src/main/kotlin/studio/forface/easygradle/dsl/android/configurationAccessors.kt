@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.dsl.*

fun DependencyHandler.androidTestImplementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        addX("androidTestImplementation", it)
    }
}
