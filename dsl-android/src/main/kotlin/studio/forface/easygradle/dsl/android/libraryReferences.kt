@file:Suppress(
    "unused" // Public API
)
package studio.forface.easygradle.dsl.android

import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.common.lateinit
import studio.forface.easygradle.dsl.dagger
import studio.forface.easygradle.dsl.dependency
import studio.forface.easygradle.dsl.forface
import studio.forface.easygradle.dsl.google
import studio.forface.easygradle.dsl.jakeWharton
import studio.forface.easygradle.dsl.squareup
import studio.forface.easygradle.dsl.version

// region Jetpack

// region Arch
val DependencyHandler.`android-arch-common` get() =         androidxArch("common")
val DependencyHandler.`android-arch-runtime` get() =        androidxArch("runtime")
val DependencyHandler.`android-arch-testing` get() =        androidxArch("testing")
var `android-arch version` by lateinit()
// endregion

// region Lifecycle
val DependencyHandler.`lifecycle-compiler` get() =          androidxLifecycle("compiler")
val DependencyHandler.`lifecycle-extensions` get() =        androidxLifecycle("extensions")
val DependencyHandler.`lifecycle-liveData` get() =          androidxLifecycle("livedata-ktx")
val DependencyHandler.`lifecycle-runtime` get() =           androidxLifecycle("runtime-ktx")
val DependencyHandler.`lifecycle-viewModel` get() =         androidxLifecycle("viewmodel-ktx")
var `lifecycle version` by lateinit()
// endregion

// region Paging
val DependencyHandler.`paging-common` get() =               androidxPaging("common-ktx")
val DependencyHandler.`paging-runtime` get() =              androidxPaging("runtime-ktx")
var `android-paging version` by lateinit()
// endregion

// region Room
val DependencyHandler.`room-runtime` get() =                androidxRoom("runtime")
val DependencyHandler.`room-compiler` get() =               androidxRoom("compiler")
val DependencyHandler.`room-ktx` get() =                    androidxRoom("ktx")
val DependencyHandler.`room-testing` get() =                androidxRoom("testing")
var `android-room version` by lateinit()
// endregion

// region Test
val DependencyHandler.`android-test-core` get() =           androidxTest("core")
val DependencyHandler.`android-test-junit` get() =          androidx("test.ext", "junit") version `android-test version`
val DependencyHandler.`android-test-rules` get() =          androidxTest("rules")
val DependencyHandler.`android-test-runner` get() =         androidxTest("runner")
var `android-test version` by lateinit()
// endregion

// region Work
val DependencyHandler.`android-work-runtime` get() =        androidxWork("runtime-ktx")
val DependencyHandler.`android-work-testing` get() =        androidxWork("testing")
var `android-work version` by lateinit()
// endregion

// region other
val DependencyHandler.`android-gradle-plugin` get() =       android("tools.build", "gradle") version `android-gradle-plugin version`
var `android-gradle-plugin version` by lateinit()


val DependencyHandler.`activity` get() =                    androidx("activity", moduleSuffix = "ktx") version `activity version`
var `activity version` by lateinit()

val DependencyHandler.`android-annotation` get() =          androidx("annotation") version `android-annotation version`
var `android-annotation version` by lateinit()

val DependencyHandler.`android-ktx` get() =                 androidx("core", moduleSuffix = "ktx") version `ktx version`
var `ktx version` by lateinit()

val DependencyHandler.`appcompat` get() =                   androidx("appcompat") version `appcompat version`
var `appcompat version` by lateinit()

val DependencyHandler.`constraint-layout` get() =           androidx("constraintlayout") version `constraint-layout version`
var `constraint-layout version` by lateinit()

val DependencyHandler.`espresso` get() =                    androidx("test.espresso", "espresso-core") version `espresso version`
var `espresso version` by lateinit()

val DependencyHandler.`fragment` get() =                    androidx("fragment", moduleSuffix = "ktx") version `fragment version`
var `fragment version` by lateinit()

val DependencyHandler.`material` get() =                    googleAndroid("material") version `material version`
var `material version` by lateinit()

val DependencyHandler.`robolectric` get() =                 dependency("org.robolectric", module = "robolectric") version `robolectric version`
var `robolectric version` by lateinit()
// endregion
// endregion

// region Google
val DependencyHandler.`dagger-android` get() =                  dagger("android")
val DependencyHandler.`dagger-android-support` get() =          dagger("android-support")
val DependencyHandler.`dagger-android-processor` get() =        dagger("android-processor")
// endregion

// region Square
val DependencyHandler.`retrofit` get() =                        squareup("retrofit2", "retrofit") version `retrofit version`
var `retrofit version` by lateinit()
// endregion

// region Jake Wharton
val DependencyHandler.`retrofit-kotlin-serialization` get() =   jakeWharton("retrofit", "retrofit2-kotlinx-serialization-converter") version `retrofit-kotlin-serialization version`
var `retrofit-kotlin-serialization version` by lateinit()

val DependencyHandler.`timber` get() =                          jakeWharton("timber") version `timber version`
var `timber version` by lateinit()
// endregion

// region 4face
val DependencyHandler.`fluentNotifications` get() =             forface("fluentnotifications") version `fluentNotifications version`
var `fluentNotifications version` by lateinit()

val DependencyHandler.`theia` get() =                           forface("theia") version `theia version`
var `theia version` by lateinit()

val DependencyHandler.`viewStateStore` get() =                  forface("viewstatestore") version `viewStateStore version`
val DependencyHandler.`viewStateStore-paging` get() =           forface("viewstatestore", moduleSuffix = "paging") version `viewStateStore version`
var `viewStateStore version` by lateinit()
// endregion


// region groups
fun DependencyHandler.android(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
        dependency("com.android", groupName, module, moduleSuffix, version)

fun DependencyHandler.googleAndroid(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
        google("android.$groupName", module ?: groupName, moduleSuffix, version)

fun DependencyHandler.androidx(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
        dependency("androidx", groupName, module, moduleSuffix, version)
// endregion

// region subgroups

// endregion

// region modules
fun DependencyHandler.androidxArch(moduleSuffix: String? = null, version: String = `android-arch version`) =
        androidx("arch.core", "core", moduleSuffix = moduleSuffix, version = version)

fun DependencyHandler.androidxLifecycle(moduleSuffix: String? = null, version: String = `lifecycle version`) =
        androidx("lifecycle", moduleSuffix = moduleSuffix, version = version)

fun DependencyHandler.androidxPaging(moduleSuffix: String? = null, version: String = `android-paging version`) =
        androidx("paging", moduleSuffix = moduleSuffix, version = version)

fun DependencyHandler.androidxRoom(moduleSuffix: String? = null, version: String = `android-room version`) =
        androidx("room", moduleSuffix = moduleSuffix, version = version)

fun DependencyHandler.androidxTest(module: String? = null, version: String = `android-test version`) =
        androidx("test", module, version = version)

fun DependencyHandler.androidxWork(moduleSuffix: String? = null, version: String = `android-work version`) =
        androidx("work", moduleSuffix = moduleSuffix, version = version)
// endregion
