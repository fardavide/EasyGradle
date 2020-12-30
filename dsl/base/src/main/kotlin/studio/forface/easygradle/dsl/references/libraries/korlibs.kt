@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit


var `klock version` by lateinit()


fun klock() = korlibs("klock") version `klock version`


// Base
fun korlibs(module: String) =
    dependency("com.soywiz.korlibs", groupName = module)
