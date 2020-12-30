@file:Suppress("unused", "PackageDirectoryMismatch", "MaxLineLength", "TooManyFunctions")

package studio.forface.easygradle.dsl.android

import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.lateinit
import studio.forface.easygradle.internal.useIfNotNull

// region 4face
var `fluentNotifications version` by lateinit()

fun fluentNotifications() = forface("fluentnotifications") version `fluentNotifications version`


var `theia version` by lateinit()

fun theia() = forface("theia") version `theia version`


var `viewStateStore version` by lateinit()

fun viewStateStore(module: String? = null) =
    forface("viewstatestore", moduleSuffix = module) version `viewStateStore version`
// endregion

// region Jetpack
var `activity version` by lateinit()

fun activity() =
    androidx("activity", moduleSuffix = "ktx") version `activity version`


var `android-arch version` by lateinit()

fun androidArch(module: String) =
    androidx("arch.core", "core", moduleSuffix = module) version `android-arch version`


var `lifecycle version` by lateinit()

fun lifecycle(module: String) =
    androidx("lifecycle", moduleSuffix = module) version `lifecycle version`


var `material version` by lateinit()

fun material() =
    googleAndroid("material") version `material version`


var `android-paging version` by lateinit()

fun paging(module: String) =
    androidx("paging", moduleSuffix = module) version `android-paging version`


var `android-room version` by lateinit()

fun room(module: String) =
    androidx("room", moduleSuffix = module) version `android-room version`


var `android-test version` by lateinit()

fun androidTest(module: String) =
    androidx("test", module) version `android-test version`


var `android-work version` by lateinit()

fun androidWork(module: String) =
    androidx("work", moduleSuffix = module) version `android-work version`


var `android-annotation version` by lateinit()

fun androidAnnotation() =
    androidx("annotation") version `android-annotation version`


var `ktx version` by lateinit()

fun androidKtx() =
    androidx("core", moduleSuffix = "ktx") version `ktx version`


var `appcompat version` by lateinit()

fun appcompat() =
    androidx("appcompat") version `appcompat version`


var `constraint-layout version` by lateinit()

fun constraintLayout() =
    androidx("constraintlayout") version `constraint-layout version`


var `espresso version` by lateinit()

fun espresso() =
    androidx("test.espresso", "espresso-core") version `espresso version`


var `fragment version` by lateinit()

fun fragment() =
    androidx("fragment", moduleSuffix = "ktx") version `fragment version`


var `robolectric version` by lateinit()

fun robolectric() =
    dependency("org.robolectric", module = "robolectric") version `robolectric version`
// endregion

// region Google
fun daggerAndroid(module: String? = null) =
    dagger("android${module.useIfNotNull { "-$it" }}")

var `hilt-android version` by lateinit()

fun hiltAndroid(module: String? = null) =
    daggerGroup("hilt-android", moduleSuffix = module) version `hilt-android version`


var `hilt-androidx version` by lateinit()

fun hiltAndroidx(module: String? = null) =
    androidx("hilt", moduleSuffix = module) version `hilt-androidx version`
// endregion

// region Square
var `retrofit version` by lateinit()

fun retrofit(module: String? = null) =
    squareup("retrofit2", "retrofit", moduleSuffix = module) version `retrofit version`
// endregion

// region Jake Wharton
var `retrofit-kotlin-serialization version` by lateinit()

fun retrofitKotlinSerialization() =
    jakeWharton(
        "retrofit",
        "retrofit2-kotlinx-serialization-converter"
    ) version `retrofit-kotlin-serialization version`


var `timber version` by lateinit()

fun timber() =
    jakeWharton("timber") version `timber version`
// endregion


// region groups
fun android(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("com.android", groupName, module, moduleSuffix, version)

fun googleAndroid(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    google("android.$groupName", module ?: groupName, moduleSuffix, version)

fun androidx(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
    dependency("androidx", groupName, module, moduleSuffix, version)
// endregion

// region subgroups
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
// endregion
