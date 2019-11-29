@file:JvmName("Dependency_ConfigurationAccessors")
@file:Suppress(
        "unused", // Public APIs
        "FunctionName", // Constructor functions
        "PackageDirectoryMismatch" // ignore `dependency` package
)
package studio.forface.easygradle.dsl

import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.project
import java.io.File

/*
 * Configuration accessors for `Dependency` and subclasses
 * Author: Davide Farella
 */

// region Remote
fun DependencyHandler.api(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency> = NoAction
) = add("api", module, dependencyConfiguration)

fun DependencyHandler.compileOnly(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency> = NoAction
) = add("compileOnly", module, dependencyConfiguration)

fun DependencyHandler.implementation(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency> = NoAction
) = add("implementation", module, dependencyConfiguration)

fun DependencyHandler.testImplementation(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency> = NoAction
) = add("testImplementation", module, dependencyConfiguration)

fun DependencyHandler.add(
        configuration: String,
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency> = NoAction
) = addDependencyTo(this, configuration, module.url(), dependencyConfiguration)
// endregion

// region Local
// region Module
fun DependencyHandler.api(
        module: LocalModuleLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit = {}
) = add("api", module, dependencyConfiguration)

fun DependencyHandler.compileOnly(
        module: LocalModuleLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit = {}
) = add("compileOnly", module, dependencyConfiguration)

fun DependencyHandler.implementation(
        module: LocalModuleLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit = {}
) = add("implementation", module, dependencyConfiguration)

fun DependencyHandler.testImplementation(
        module: LocalModuleLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit = {}
) = add("testImplementation", module, dependencyConfiguration)

fun DependencyHandler.add(
        configuration: String,
        module: LocalModuleLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit = {}
) = add(configuration, project(module.path()), dependencyConfiguration)
// endregion

// region File
fun DependencyHandler.api(fileLibrary: LocalFileLibrary) = add("api", fileLibrary)

fun DependencyHandler.compileOnly(fileLibrary: LocalFileLibrary) = add("compileOnly", fileLibrary)

fun DependencyHandler.implementation(fileLibrary: LocalFileLibrary) = add("implementation", fileLibrary)

fun DependencyHandler.testImplementation(fileLibrary: LocalFileLibrary) = add("testImplementation", fileLibrary)

fun DependencyHandler.addFile(
        configuration: String,
        fileLibrary: LocalFileLibrary
) = add(configuration, File(fileLibrary.path()))
// endregion
// endregion

internal object NoAction : Action<ExternalModuleDependency> {
    override fun execute(p0: ExternalModuleDependency) {}
}