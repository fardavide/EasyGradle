@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl


// Base
fun jakeWharton(
    groupName: String? = null,
    module: String? = null,
    moduleSuffix: String? = null,
    version: String? = null
) = dependency("com.jakewharton", groupName, module, moduleSuffix, version)
