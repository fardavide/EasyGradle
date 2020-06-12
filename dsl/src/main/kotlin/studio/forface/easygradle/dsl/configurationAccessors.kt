@file:Suppress("unused")

package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.dsl.DependencyHandler

// TODO Excluded deps

fun DependencyHandler.api(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        add("api", it)
    }
}

fun DependencyHandler.compileOnly(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        add("compileOnly", it)
    }
}

fun DependencyHandler.implementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.kapt(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        add("kapt", it)
    }
}

fun DependencyHandler.testImplementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        add("testImplementation", it)
    }
}
