@file:Suppress(
        "unused" // Public APIs
)

package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.api(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        when(it) {
            is LocalLibrary -> api(it)
            is RemoteLibrary -> api(it)
            else -> add("api", it)
        }
    }
}

fun DependencyHandler.compileOnly(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        when(it) {
            is LocalLibrary -> compileOnly(it)
            is RemoteLibrary -> compileOnly(it)
            else -> add("compileOnly", it)
        }
    }
}

fun DependencyHandler.implementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        when(it) {
            is LocalLibrary -> implementation(it)
            is RemoteLibrary -> implementation(it)
            else -> add("implementation", it)
        }
    }
}

fun DependencyHandler.testImplementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        when(it) {
            is LocalLibrary -> testImplementation(it)
            is RemoteLibrary -> testImplementation(it)
            else -> add("testImplementation", it)
        }
    }
}