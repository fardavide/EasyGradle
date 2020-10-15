@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import org.gradle.api.Project
import studio.forface.easygradle.dsl.*

/*
 * Set of classes and interfaces that suits as constructor for a Local Dependency
 * Author: Davide Farella
 */

fun Project.aar(
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
    extension = "aar"
)
