@file:Suppress(
        "unused", // Public APIs
        "FunctionName", // Constructor functions
        "PackageDirectoryMismatch" // ignore `dependency` package
)
package studio.forface.easygradle.dsl

import org.gradle.api.Project
import studio.forface.easygradle.dsl.Dependency.SourceType.Local
import studio.forface.easygradle.dsl.Dependency.SourceType.Remote

/**
 * A generic dependency
 * @author Davide Farella
 */
interface Dependency {
    /** @see SourceType */
    val sourceType: SourceType

    /**
     * Type of the source of the [Dependency].
     * It could be [Local] ( probably hosted in the same project ) or [Remote] ( hosted on the net )
     */
    enum class SourceType { Local, Remote }
}

/**
 * A [Dependency] hosted locally.
 * It is generally a module or a file
 */
interface LocalDependency : Dependency {
    override val sourceType get() = Local

    /** Name of this module [Dependency] */
    val name: String

    /** The path to the [Dependency] */
    val path get() = name

    /** Resolves the Dependency notation for this [LocalDependency] */
    fun source(): Any
}

/**
 * A [LocalDependency] included into a module
 */
interface ModuleDependency : LocalDependency {

    /** Optional parent module [ModuleDependency] for this Dependency */
    val parent: ModuleDependency? get() = null

    /** @return the path to the [Dependency]. E.g. `` ":sharedTest:testKotlin" `` */
    override val path: String
        get() = "${parent?.path ?: ""}:$name"
}

/**
 * A [LocalDependency] included into a file
 */
interface FileDependency : LocalDependency {

    val projectDirectoryPath: String
    val subDirectoryPath: String
    val versionSeparator: String
    val version: String
    val extension: String

    /** @return the path to the [Dependency]. E.g. `` ":sharedTest:testKotlin" `` */
    override val path
        get() = (if (projectDirectoryPath.isNotBlank()) "$projectDirectoryPath/" else "") +
                "$subDirectoryPath/$name$versionSeparator$version.$extension"
}

/**
 * A [Dependency] hosted remotely
 */
interface RemoteDependency : Dependency {
    override val sourceType get() = Remote

    /** Group name of this [Dependency] */
    val group: String

    /** Module name of this [Dependency] */
    val module: String

    /** Version of this [Dependency] */
    val version: String

    /** @return the url to the [Dependency] */
    fun url() = "$group:$module:$version"
}

// region Library
/**
 * A library that can be included into `dependencies` block of a module
 * @see Dependency
 */
interface Library : Dependency

/**
 * A [Library] hosted locally
 * @see LocalDependency
 */
interface LocalLibrary : Library, LocalDependency

/**
 * A [LocalLibrary] from a module
 * @see ModuleDependency
 */
interface LocalModuleLibrary : LocalLibrary, ModuleDependency
fun Project.LocalModuleLibrary(name: String) = object : LocalModuleLibrary {
    override val name = name
    override fun source() = project(name)
}

/**
 * A [LocalLibrary] from a file
 * @see FileDependency
 */
interface LocalFileLibrary : LocalLibrary, FileDependency
fun Project.LocalFileLibrary(
    projectDirectoryPath: String,
    subDirectoryPath: String,
    name: String,
    versionSeparator: String,
    version: String,
    extension: String
) = object : LocalFileLibrary {
    override val projectDirectoryPath = projectDirectoryPath
    override val subDirectoryPath = subDirectoryPath
    override val name = name
    override val versionSeparator = versionSeparator
    override val version = version
    override val extension = extension

    override fun source() = files(path)
}

/**
 * A [Library] hosted remotely
 * @see RemoteDependency
 */
interface RemoteLibrary : Library, RemoteDependency
fun RemoteLibrary(group: String, module: String, version: String) = object : RemoteLibrary {
    override val group = group
    override val module = module
    override val version = version
}
// endregion

// region Plugin
/**
 * A generic plugin
 * @see Dependency
 */
interface Plugin : Dependency

/**
 * A [Plugin] that can be applied to a single module
 */
interface ModulePlugin : Plugin {

    /** Identifier of the plugin */
    val id: String

    /** Optional version of the plugin */
    val version: String? get() = null
}

/**
 * A [ModulePlugin] hosted locally
 * @see Plugin
 */
interface LocalModulePlugin : ModulePlugin

/**
 * A [ModulePlugin] hosted remotely
 * @see Plugin
 */
interface RemoteModulePlugin : ModulePlugin {
    override val sourceType get() = Remote
}
fun RemoteModulePlugin(id: String, version: String? = null) = object : RemoteModulePlugin {
    override val id = id
    override val version = version
}

/**
 * A [Plugin] that can be applied to the whole project
 */
interface ProjectPlugin : Plugin

/**
 * A [ProjectPlugin] hosted locally
 * @see Plugin
 */
interface LocalProjectPlugin : ProjectPlugin

/**
 * A [ProjectPlugin] hosted remotely
 * @see Plugin
 * @see RemoteDependency
 */
interface RemoteProjectPlugin : ProjectPlugin, RemoteDependency
fun RemoteProjectPlugin(group: String, module: String, version: String) = object : RemoteProjectPlugin {
    override val group = group
    override val module = module
    override val version = version
}

/**
 * A [Plugin] that works as [ModulePlugin] and [ProjectPlugin]
 */
interface GradlePlugin : ModulePlugin, ProjectPlugin

/**
 * A [GradlePlugin] hosted locally
 * @see Plugin
 * @see LocalModulePlugin
 * @see LocalProjectPlugin
 */
interface LocalGradlePlugin : GradlePlugin, LocalModulePlugin, LocalProjectPlugin {
    override val sourceType get() = Local
}

/**
 * A [GradlePlugin] hosted locally
 * @see Plugin
 * @see LocalModulePlugin
 * @see LocalProjectPlugin
 */
interface RemoteGradlePlugin : GradlePlugin, RemoteModulePlugin, RemoteProjectPlugin {
    override val sourceType get() = Remote
    override val version: String
}
fun RemotePlugin(group: String, module: String, version: String, id: String) = object : RemoteGradlePlugin {
    override val group = group
    override val module = module
    override val id = id
    override val version = version
}
// endregion
