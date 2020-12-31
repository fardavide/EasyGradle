@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.lateinit


var `accompanist version` by lateinit()


fun accompanist(module: String) =
    dependency(
        "dev.chrisbanes",
        groupName = "accompanist",
        moduleSuffix = module
    ) version `accompanist version`
