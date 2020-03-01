@file:Suppress(
        "unused", // Public APIs
        "PackageDirectoryMismatch" // ignore `dependency` package
)

package studio.forface.easygradle.dsl

import org.gradle.api.Project

/*
 * Set of classes and interfaces that suits as constructor for a `LocalDependency`
 * Author: Davide Farella
 */

// region Constructors
fun Project.jar(
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
        extension =             "jar"
)

/** @return [LocalFileLibrary] */
fun Project.fileLibrary(
    name: String,
    extension: String,
    version: String = "",
    versionSeparator: String = if (version.isNotBlank()) "_" else "",
    projectDirectoryPath: String = projectDir.path,
    subDirectoryPath: String = "libs"
) = LocalFileLibrary(
        projectDirectoryPath =  projectDirectoryPath,
        subDirectoryPath =      subDirectoryPath,
        name =                  name,
        versionSeparator =      versionSeparator,
        version =               version,
        extension =             extension
)
// endregion
