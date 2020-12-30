@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.lateinit


var `activity version` by lateinit()
var `android-annotation version` by lateinit()
var `android-arch version` by lateinit()
var `android-paging version` by lateinit()
var `android-room version` by lateinit()
var `android-test version` by lateinit()
var `android-work version` by lateinit()
var `appcompat version` by lateinit()
var `constraint-layout version` by lateinit()
var `espresso version` by lateinit()
var `fragment version` by lateinit()
var `ktx version` by lateinit()
var `lifecycle version` by lateinit()
var `material version` by lateinit()
var `robolectric version` by lateinit()


fun activity() =
    androidx("activity", moduleSuffix = "ktx") version `activity version`

fun androidAnnotation() =
    androidx("annotation") version `android-annotation version`

fun androidArch(module: String) =
    androidx("arch.core", "core", moduleSuffix = module) version `android-arch version`

fun androidKtx() =
    androidx("core", moduleSuffix = "ktx") version `ktx version`

fun androidTest(module: String) =
    androidx("test", module) version `android-test version`

fun androidWork(module: String) =
    androidx("work", moduleSuffix = module) version `android-work version`

fun appcompat() =
    androidx("appcompat") version `appcompat version`

fun constraintLayout() =
    androidx("constraintlayout") version `constraint-layout version`

fun espresso() =
    androidx("test.espresso", "espresso-core") version `espresso version`

fun fragment() =
    androidx("fragment", moduleSuffix = "ktx") version `fragment version`

fun lifecycle(module: String) =
    androidx("lifecycle", moduleSuffix = module) version `lifecycle version`

fun material() =
    googleAndroid("material") version `material version`

fun paging(module: String) =
    androidx("paging", moduleSuffix = module) version `android-paging version`

fun robolectric() =
    dependency("org.robolectric", module = "robolectric") version `robolectric version`

fun room(module: String) =
    androidx("room", moduleSuffix = module) version `android-room version`


// Base
fun googleAndroid(
    groupName: String? = null,
    module: String? = null,
    moduleSuffix: String? = null,
    version: String? = null
) = google("android.$groupName", module ?: groupName, moduleSuffix, version)

fun androidx(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("androidx", groupName, module, moduleSuffix, version)

fun androidxArch(moduleSuffix: String? = null, version: String = `android-arch version`) =
    androidx("arch.core", "core", moduleSuffix = moduleSuffix, version = version)

fun androidxLifecycle(moduleSuffix: String? = null, version: String = `lifecycle version`) =
    androidx("lifecycle", moduleSuffix = moduleSuffix, version = version)

fun androidxPaging(moduleSuffix: String? = null, version: String = `android-paging version`) =
    androidx("paging", moduleSuffix = moduleSuffix, version = version)

fun androidxRoom(moduleSuffix: String? = null, version: String = `android-room version`) =
    androidx("room", moduleSuffix = moduleSuffix, version = version)

fun androidxTest(module: String? = null, version: String = `android-test version`) =
    androidx("test", module, version = version)

fun androidxWork(moduleSuffix: String? = null, version: String = `android-work version`) =
    androidx("work", moduleSuffix = moduleSuffix, version = version)
