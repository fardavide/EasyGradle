@file:Suppress("unused")

// Public APIs

package studio.forface.easygradle.dsl

import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.project

// region Remote
fun DependencyHandler.api(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency>
) = add("api", module, dependencyConfiguration)

fun DependencyHandler.compileOnly(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency>
) = add("compileOnly", module, dependencyConfiguration)

fun DependencyHandler.implementation(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency>
) = add("implementation", module, dependencyConfiguration)

fun DependencyHandler.testImplementation(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency>
) = add("testImplementation", module, dependencyConfiguration)

fun DependencyHandler.add(
        configuration: String,
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency>
) = addDependencyTo(this, configuration, module.url(), dependencyConfiguration)
// endregion

// region Local
fun DependencyHandler.api(
        module: LocalLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit
) = add("api", module, dependencyConfiguration)

fun DependencyHandler.compileOnly(
        module: LocalLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit
) = add("compileOnly", module, dependencyConfiguration)

fun DependencyHandler.implementation(
        module: LocalLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit
) = add("implementation", module, dependencyConfiguration)

fun DependencyHandler.testImplementation(
        module: LocalLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit
) = add("testImplementation", module, dependencyConfiguration)

fun DependencyHandler.add(
        configuration: String,
        module: LocalLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit
) = add(configuration, project(module.path()), dependencyConfiguration)
// endregion
