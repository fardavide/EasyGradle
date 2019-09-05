@file:Suppress("unused") // Public APIs

package studio.forface.easygradle.dsl

/*
 * Set of classes and interfaces that suits as constructor for a `RemoteDependency`
 * Author: Davide Farella
 */

/**
 * TODO
 */
interface Domain {

    /** Version of the dependency */
    val version: String

    /** @return [List] of all the [Group] for this [Domain] */
    fun allGroups(): List<Group>

    /** @return [List] of all the [RemoteDependency] for this [Domain] */
    fun allModules() = allGroups().flatMap { it.all() }


    // region Constructor functions
    /** @return [RemoteLibrary] created with receiver [Group] as parent */
    fun Group.library(module: String) = RemoteLibrary(name, module, version)

    /** @return [RemoteGradlePlugin] created with receiver [Group] as parent */
    fun Group.plugin(module: String, pluginId: String) = RemotePlugin(name, module, version, pluginId)

    /** @return [RemoveProjectPlugin] created with receiver [Group] as parent */
    fun Group.projectPlugin(module: String) = RemoveProjectPlugin(name, module, version)

    /** @return [RemoteModulePlugin] with version of [Domain] */
    fun modulePlugin(pluginId: String) = RemoteModulePlugin(pluginId, version)
    // endregion
}


/** Group of a [Dependency] */
abstract class Group(val name: String) {

    /** @return [List] of all the [RemoteDependency] for this [Group] */
    abstract fun all(): List<RemoteDependency>

    /** Default implementation of [Domain.allGroups] */
    open fun allGroups(): List<Group> = listOf(this)

    /** @return name of the group. [name] */
    override fun toString() = name
}

// region Constructors
/** @return [RemoteModulePlugin] */
fun modulePlugin(pluginId: String) = RemoteModulePlugin(pluginId)
// endregion
