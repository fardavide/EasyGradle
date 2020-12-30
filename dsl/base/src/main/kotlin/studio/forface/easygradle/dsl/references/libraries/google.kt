@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit


var `dagger version` by lateinit()


fun dagger(moduleSuffix: String? = null) =
    daggerGroup("dagger", moduleSuffix = moduleSuffix) version `dagger version`


// Base
fun google(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.google", groupName, module, moduleSuffix, version)

fun daggerGroup(module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    google("dagger", module = module, moduleSuffix = moduleSuffix, version = version)
