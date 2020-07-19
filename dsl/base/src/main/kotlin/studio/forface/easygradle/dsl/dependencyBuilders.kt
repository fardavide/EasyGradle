@file:Suppress("unused")

package studio.forface.easygradle.dsl

import org.gradle.api.Project

/*
 * Set of classes and interfaces that suits as constructor for a Local Dependency
 * Author: Davide Farella
 */

fun Project.jar(
    name: String,
    version: String = "",
    versionSeparator: String = if (version.isNotBlank()) "_" else "",
    projectDirectoryPath: String = projectDir.path,
    subDirectoryPath: String = "libs"
) = fileLibrary(
    projectDirectoryPath = projectDirectoryPath,
    subDirectoryPath = subDirectoryPath,
    name = name,
    versionSeparator = versionSeparator,
    version = version,
    extension = "jar"
)

fun Project.fileLibrary(
    name: String,
    extension: String,
    version: String = "",
    versionSeparator: String = if (version.isNotBlank()) "_" else "",
    projectDirectoryPath: String = projectDir.path,
    subDirectoryPath: String = "libs"
) = files(
    (if (projectDirectoryPath.isNotBlank()) "$projectDirectoryPath/" else "") +
        "$subDirectoryPath/$name$versionSeparator$version.$extension"
)
