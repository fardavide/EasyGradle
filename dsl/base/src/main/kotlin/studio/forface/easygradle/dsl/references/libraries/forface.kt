@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit


var `assert4k version` by lateinit()

fun assert4k() =
    forface(module = "assert4k") version `assert4k version`

// Base
fun forface(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("studio.forface", groupName, module, moduleSuffix, version)
