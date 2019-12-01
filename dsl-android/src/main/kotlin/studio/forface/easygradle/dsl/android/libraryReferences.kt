@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package studio.forface.easygradle.dsl.android

import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.dsl.*

// region Jetpack
val DependencyHandler.`android-ktx` get() =                 androidx("core", moduleSuffix = "ktx") version `ktx version`
var `ktx version` = defaultKtxVersion

val DependencyHandler.`android-test-core` get() =           androidxTest("core")
val DependencyHandler.`android-test-junit` get() =          androidx("test.ext", "junit") version `android-test version`
val DependencyHandler.`android-test-rules` get() =          androidxTest("rules")
val DependencyHandler.`android-test-runner` get() =         androidxTest("runner")
var `android-test version` = defaultTestVersion

val DependencyHandler.`android-annotation` get() =          androidx("annotation") version `android-annotation version`
var `android-annotation version` = defaultAnnotationVersion

val DependencyHandler.`android-arch-common` get() =         androidxArch("common")
val DependencyHandler.`android-arch-runtime` get() =        androidxArch("runtime")
val DependencyHandler.`android-arch-testing` get() =        androidxArch("testing")
var `android-arch version` = defaultArchVersion

val DependencyHandler.`appcompat` get() =                   androidx("appcompat") version `appcompat version`
var `appcompat version` = defaultSupportVersion

val DependencyHandler.`constraint-layout` get() =           androidx("constraintlayout") version `constraint-layout version`
var `constraint-layout version` = defaultConstraintLayoutVersion

val DependencyHandler.`espresso` get() =                    androidx("test.espresso", "espresso-core") version `espresso version`
var `espresso version` = defaultEspressoVersion

val DependencyHandler.`lifecycle-compiler` get() =          androidxLifecycle("compiler")
val DependencyHandler.`lifecycle-extensions` get() =        androidxLifecycle("extensions")
val DependencyHandler.`lifecycle-liveData` get() =          androidxLifecycle("livedata-ktx")
val DependencyHandler.`lifecycle-runtime` get() =           androidxLifecycle("runtime-ktx")
val DependencyHandler.`lifecycle-viewModel` get() =         androidxLifecycle("viewmodel-ktx")
var `lifecycle version` = defaultLifecycleVersion

val DependencyHandler.`material` get() =                    googleAndroid("material") version `material version`
var `material version` = defaultMaterialVersion

val DependencyHandler.`paging-common` get() =               androidxPaging("common-ktx")
val DependencyHandler.`paging-runtime` get() =              androidxPaging("runtime-ktx")
var `android-paging version` = defaultPagingVersion

val DependencyHandler.`room-runtime` get() =                androidxRoom("runtime")
val DependencyHandler.`room-compiler` get() =               androidxRoom("compiler")
val DependencyHandler.`room-ktx` get() =                    androidxRoom("ktx")
val DependencyHandler.`room-testing` get() =                androidxRoom("testing")
var `android-room version` = defaultRoomVersion

val DependencyHandler.`android-work-runtime` get() =        androidxWork("runtime-ktx")
val DependencyHandler.`android-work-testing` get() =        androidxWork("testing")
var `android-work version` = defaultWorkVersion

val DependencyHandler.`android-gradle-plugin` get() =       android("tools.build", "gradle") version `android-gradle-plugin version`
var `android-gradle-plugin version` = defaultAgpVersion
// endregion

// region Google
val DependencyHandler.`dagger-android` get() =                  dagger("android")
val DependencyHandler.`dagger-android-support` get() =          dagger("android-support")
val DependencyHandler.`dagger-android-processor` get() =        dagger("android-processor")
// endregion

// region Square
val DependencyHandler.`retrofit` get() =                        squareup("retrofit2", "retrofit") version `retrofit version`

var `retrofit version` = defaultRetrofitVersion
// endregion

// region Jake Wharton
val DependencyHandler.`retrofit-kotlin-serialization` get() =   jakeWharton("retrofit", "retrofit2-kotlinx-serialization-converter") version `retrofit-kotlin-serialization version`

var `retrofit-kotlin-serialization version` = defaultRetrofitKotlinSerializationVersion
// endregion

// region 4face
val DependencyHandler.`fluentNotifications` get() =             forface("fluentnotifications") version `fluentNotifications version`

var `fluentNotifications version` = defaultFluentNotificationsVersion

val DependencyHandler.`viewStateStore` get() =                  forface("viewstatestore") version `viewStateStore version`
val DependencyHandler.`viewStateStore-paging` get() =           forface("viewstatestore", moduleSuffix = "paging") version `viewStateStore version`

var `viewStateStore version` = defaultViewStateStoreVersion
// endregion


// region groups
fun DependencyHandler.android(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
        dependency("com.android", groupName, module, moduleSuffix, version)

fun DependencyHandler.googleAndroid(groupName: String? = null, module: String? = null, moduleSuffix: String? = null, version: String? = null) =
        google("android.$groupName", module, moduleSuffix, version)

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
