@file:Suppress("unused")

// Public APIs

package studio.forface.easygradle.dsl.android

import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo
import org.gradle.kotlin.dsl.add
import org.gradle.kotlin.dsl.project
import studio.forface.easygradle.dsl.LocalLibrary
import studio.forface.easygradle.dsl.RemoteLibrary
import studio.forface.easygradle.dsl.add

// region Remote
fun DependencyHandler.androidTestImplementation(
        module: RemoteLibrary,
        dependencyConfiguration: Action<ExternalModuleDependency>
) = add("androidTestImplementation", module, dependencyConfiguration)
// endregion

// region Local
fun DependencyHandler.androidTestImplementation(
        module: LocalLibrary,
        dependencyConfiguration: ProjectDependency.() -> Unit
) = add("androidTestImplementation", module, dependencyConfiguration)
// endregion
