@file:Suppress(
        "unused" // Public APIs
)
package studio.forface.easygradle.dsl.android

import org.gradle.api.Project
import studio.forface.easygradle.dsl.fileLibrary

/*
 * Set of classes and interfaces that suits as constructor for a `LocalDependency`
 * Author: Davide Farella
 */

// region Constructors
fun Project.aar(
    name: String,
    version: String = "",
    versionSeparator: String = if (version.isNotBlank()) "_" else "",
    projectDirectoryPath: String = projectDir.path,
    subDirectoryPath: String = "libs"
) = fileLibrary(
        projectDirectoryPath =  projectDirectoryPath,
        subDirectoryPath =      subDirectoryPath,
        name =                  name,
        versionSeparator =      versionSeparator,
        version =               version,
        extension =             "aar"
)
// endregion
