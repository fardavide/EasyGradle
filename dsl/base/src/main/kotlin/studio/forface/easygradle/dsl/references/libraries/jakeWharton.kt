@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit


var `picnic version` by lateinit()


fun picnic() =
    jakeWharton("picnic") version `picnic version`


// Base
fun jakeWharton(
    groupName: String? = null,
    module: String? = null,
    moduleSuffix: String? = null,
    version: String? = null
) = dependency("com.jakewharton", groupName, module, moduleSuffix, version)
