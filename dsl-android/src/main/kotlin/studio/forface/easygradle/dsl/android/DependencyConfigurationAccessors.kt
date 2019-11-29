@file:Suppress(
        "unused" // Public APIs
)
package studio.forface.easygradle.dsl.android

import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.dsl.*

// region Remote
fun DependencyHandler.androidTestImplementation(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency>
) = add("androidTestImplementation", module, dependencyConfiguration)
// endregion

// region Local
fun DependencyHandler.androidTestImplementation(
        module: LocalModuleLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit
) = add("androidTestImplementation", module, dependencyConfiguration)

fun DependencyHandler.androidTestImplementation(fileLibrary: LocalFileLibrary) =
        addFile("androidTestImplementation", fileLibrary)
// endregion
