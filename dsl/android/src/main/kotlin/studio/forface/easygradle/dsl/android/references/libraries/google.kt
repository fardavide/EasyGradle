@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.lateinit
import studio.forface.easygradle.internal.useIfNotNull


var `hilt-android version` by lateinit()
var `hilt-androidx version` by lateinit()


fun daggerAndroid(module: String? = null) =
    dagger("android${module.useIfNotNull { "-$it" }}")

fun hiltAndroid(module: String? = null) =
    daggerGroup("hilt-android", moduleSuffix = module) version `hilt-android version`

fun hiltAndroidx(module: String? = null) =
    androidx("hilt", moduleSuffix = module) version `hilt-androidx version`
