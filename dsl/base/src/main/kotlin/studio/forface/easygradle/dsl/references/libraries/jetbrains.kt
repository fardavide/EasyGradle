@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit


var `kotlin version` by lateinit()
var `coroutines version` by lateinit()
var `serialization version` by lateinit()
var `ktor version` by lateinit()


fun kotlin(moduleSuffix: String? = null) =
    jetbrains("kotlin", moduleSuffix = moduleSuffix) version `kotlin version`

fun coroutines(moduleSuffix: String? = null) =
    kotlinx("coroutines-$moduleSuffix") version `coroutines version`

fun serialization(module: String) =
    kotlinx("serialization-$module") version `serialization version`

// Ktor
fun ktorClient(module: String) =
    ktor("client-$module")

fun ktorServer(module: String) =
    ktor("server-$module")

fun ktor(module: String) =
    dependency("io", groupName = "ktor", moduleSuffix = module) version `ktor version`

// Base
fun kotlinx(moduleSuffix: String? = null, version: String? = null) =
    jetbrains("kotlinx", moduleSuffix = moduleSuffix, version = version)

fun jetbrains(
    groupName: String? = null,
    module: String? = null,
    moduleSuffix: String? = null,
    version: String? = null
) = dependency("org.jetbrains", groupName, module, moduleSuffix, version)

