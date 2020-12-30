@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit


var `assistedInject version` by lateinit()
var `kotlinPoet version` by lateinit()
var `sqlDelight version` by lateinit()


fun assistedInject(module: String) =
    squareup("inject", module = module) version `assistedInject version`

fun kotlinPoet(moduleSuffix: String? = null) =
    squareup("kotlinpoet", moduleSuffix = moduleSuffix) version `kotlinPoet version`

fun sqlDelight(module: String? = null) =
    squareup("sqldelight", module = module) version `sqlDelight version`


// Base
fun squareup(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.squareup", groupName, module, moduleSuffix, version)
