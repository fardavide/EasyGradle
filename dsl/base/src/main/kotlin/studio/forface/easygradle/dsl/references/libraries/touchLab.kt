@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit


var `kermit version` by lateinit()


fun kermit() = touchLab("kermit") version `kermit version`


// Base
fun touchLab(module: String) =
    dependency("co.touchlab", module = module)
