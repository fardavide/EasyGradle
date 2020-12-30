@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.lateinit


var `retrofit version` by lateinit()


fun retrofit(module: String? = null) =
    squareup("retrofit2", "retrofit", moduleSuffix = module) version `retrofit version`
