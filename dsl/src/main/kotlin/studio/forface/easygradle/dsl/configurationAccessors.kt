@file:Suppress("unused")

package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.api(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        addX("api", it)
    }
}

fun DependencyHandler.compileOnly(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        addX("compileOnly", it)
    }
}

fun DependencyHandler.implementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        addX("implementation", it)
    }
}

fun DependencyHandler.kapt(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        addX("kapt", it)
    }
}

fun DependencyHandler.testImplementation(vararg dependencyNotations: Any) {
    dependencyNotations.forEach {
        addX("testImplementation", it)
    }
}

fun DependencyHandler.addX(configurationName: String, notation: Any) {
    if (notation is ExcludingDependency) add("testImplementation", notation.notation)
        .exclude(notation.excludeNotations)
    else add(configurationName, notation)
}

private fun Dependency?.exclude(notations: List<Any>) {
    this?.exclude(*notations.toTypedArray())
}
