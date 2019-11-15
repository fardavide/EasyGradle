@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package studio.forface.easygradle.dsl.android

import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.dsl.squareup

// region Jetpack

// region Ktx
/**
 * Builds the dependency notation for Core-ktx.
 * @see androidx
 *
 * You can also use `` `android-ktx` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-ktx` get() = androidx("core", "core-ktx", version = `ktx version`)

var `ktx version` = defaultKtxVersion
// endregion

// region Test
/**
 * Builds the dependency notation for Androidx-test-core.
 * @see androidxTest
 *
 * You can also use `` `android-test-core` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-test-core` get() = androidxTest("core", version = `android-test version`)

/**
 * Builds the dependency notation for Androidx-test-junit.
 * @see androidx
 *
 * You can also use `` `android-test-junit` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-test-junit` get() = androidx("test.ext", "junit", version = `android-test version`)

/**
 * Builds the dependency notation for Androidx-test-rules.
 * @see androidxTest
 *
 * You can also use `` `android-test-rules` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-test-rules` get() = androidxTest("rules", version = `android-test version`)

/**
 * Builds the dependency notation for Androidx-test-runner.
 * @see androidxTest
 *
 * You can also use `` `android-test-runner` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-test-runner` get() = androidxTest("runner", version = `android-test version`)

var `android-test version` = defaultTestVersion
// endregion

// region Annotation
/**
 * Builds the dependency notation for Annotation.
 * @see androidx
 *
 * You can also use `` `annotation` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-annotation` get() = androidx("annotation", version = `android-annotation version`)

var `android-annotation version` = defaultAnnotationVersion
// endregion

// region Arch
/**
 * Builds the dependency notation for Arch-core-common.
 * @see androidxArch
 *
 * You can also use `` `android-arch-common` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-arch-common` get() = androidxArch("common", version = `android-arch version`)

/**
 * Builds the dependency notation for Arch-core-runtime.
 * @see androidxArch
 *
 * You can also use `` `android-arch-runtime` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-arch-runtime` get() = androidxArch("runtime", version = `android-arch version`)

/**
 * Builds the dependency notation for Arch-core-testing.
 * @see androidxArch
 *
 * You can also use `` `android-arch-testing` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-arch-testing` get() = androidxArch("testing", version = `android-arch version`)

var `android-arch version` = defaultArchVersion
// endregion

// region AppCompat
/**
 * Builds the dependency notation for AppCompat.
 * @see androidx
 *
 * You can also use `` `appcompat` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`appcompat` get() = androidx("appcompat", version = `appcompat version`)

var `appcompat version` = defaultSupportVersion
// endregion

// region Constraint Layout
/**
 * Builds the dependency notation for ConstraintLayout.
 * @see androidx
 *
 * You can also use `` `constraint-layout` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`constraint-layout` get() = androidx("constraintlayout", version = `constraint-layout version`)

var `constraint-layout version` = defaultConstraintLayoutVersion
// endregion

// region Espresso
/**
 * Builds the dependency notation for Espresso-core.
 * @see androidx
 *
 * You can also use `` `espresso` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`espresso` get() = androidx("test.espresso", "espresso-core", version = `espresso version`)

var `espresso version` = defaultEspressoVersion
// endregion

// region Lifecycle
/**
 * Builds the dependency notation for Lifecycle-compiler.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-compiler` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-compiler` get() = androidxLifecycle("compiler", version = `lifecycle version`)

/**
 * Builds the dependency notation for Lifecycle-extensions.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-extensions` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-extensions` get() = androidxLifecycle("extensions", version = `lifecycle version`)

/**
 * Builds the dependency notation for Lifecycle-livedata.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-liveData` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-liveData` get() = androidxLifecycle("livedata-ktx", version = `lifecycle version`)

/**
 * Builds the dependency notation for Lifecycle-runtime.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-runtime` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-runtime` get() = androidxLifecycle("runtime-ktx", version = `lifecycle version`)

/**
 * Builds the dependency notation for Lifecycle-viewmodel.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-viewModel` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-viewModel` get() = androidxLifecycle("viewmodel-ktx", version = `lifecycle version`)

var `lifecycle version` = defaultLifecycleVersion
// endregion

// region Material
/**
 * Builds the dependency notation for Material.
 * @see googleAndroid
 *
 * You can also use `` `material` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`material` get() = googleAndroid("material", version = `material version`)

var `material version` = defaultMaterialVersion
// endregion

// region Paging
/**
 * Builds the dependency notation for Paging-common.
 * @see androidxPaging
 *
 * You can also use `` `paging-common` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`paging-common` get() = androidxPaging("common-ktx", version = `android-paging version`)

/**
 * Builds the dependency notation for Paging-runtime.
 * @see androidxPaging
 *
 * You can also use `` `paging-runtime` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`paging-runtime` get() = androidxPaging("runtime-ktx", version = `android-paging version`)

var `android-paging version` = defaultPagingVersion
// endregion

// region Room
/**
 * Builds the dependency notation for Room-runtime.
 * @see androidxRoom
 *
 * You can also use `` `room-runtime` version "2.2.1" `` if you want to use an explicit version.
 */
val DependencyHandler.`room-runtime` get() = androidxRoom("runtime", version = `android-room version`)

/**
 * Builds the dependency notation for Room-compiler.
 * @see androidxRoom
 *
 * You can also use `` `room-compiler` version "2.2.1" `` if you want to use an explicit version.
 */
val DependencyHandler.`room-compiler` get() = androidxRoom("compiler", version = `android-room version`)

/**
 * Builds the dependency notation for Room-ktx.
 * @see androidxRoom
 *
 * You can also use `` `room-ktx` version "2.2.1" `` if you want to use an explicit version.
 */
val DependencyHandler.`room-ktx` get() = androidxRoom("ktx", version = `android-room version`)

/**
 * Builds the dependency notation for Room-testing.
 * @see androidxRoom
 *
 * You can also use `` `room-testing` version "2.2.1" `` if you want to use an explicit version.
 */
val DependencyHandler.`room-testing` get() = androidxRoom("testing", version = `android-room version`)


var `android-room version` = defaultPagingVersion
// endregion

// region WorkManager
/**
 * Builds the dependency notation for Android-work-runtime.
 * @see androidxWork
 *
 * You can also use `` `android-work-runtime` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-work-runtime` get() = androidxWork("runtime-ktx", version = `android-work version`)

/**
 * Builds the dependency notation for Android-work-testing.
 * @see androidxWork
 *
 * You can also use `` `android-work-testing` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-work-testing` get() = androidxWork("testing", version = `android-work version`)

var `android-work version` = defaultWorkVersion
// endregion

// region AGP
/**
 * Builds the dependency notation for Android Gradle plugin.
 * @see android
 *
 * You can also use `` `android-gradle-plugin` version "3.5.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-gradle-plugin` get() = android("tools.build", module = "gradle", version = `android-gradle-plugin version`)

var `android-gradle-plugin version` = defaultAgpVersion
// endregion


// region Groups
/**
 * Builds the dependency notation for the named Android group at the given [version].
 *
 * @param groupName simple name of the Android group, for example "tools.build"
 * @param module simple name of the Android module, for example "gradle". Default is [groupName]
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.android(groupName: String, module: String = groupName, version: String? = null): Any =
        "com.android.$groupName:$module${version?.let { ":$version" } ?: "" }"

/**
 * Builds the dependency notation for the named Android group at the given [version].
 *
 * @param groupName simple name of the Google-android group, for example "material"
 * @param module simple name of the Google-android module, for example "appcompat-resources". Default is [groupName]
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.googleAndroid(groupName: String, module: String = groupName, version: String? = null): Any =
        "com.google.android.$groupName:$module${version?.let { ":$version" } ?: "" }"

/**
 * Builds the dependency notation for the named Androidx group at the given [version].
 *
 * @param groupName simple name of the Androidx group, for example "appcompat"
 * @param module simple name of the Androidx module, for example "appcompat-resources". Default is [groupName]
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidx(groupName: String, module: String = groupName, version: String? = null): Any =
        "androidx.$groupName:$module${version?.let { ":$version" } ?: "" }"

/**
 * Builds the dependency notation for the named Androidx arch-core group at the given [version].
 *
 * @param module simple name of the Androidx arch-core module, for example "testing".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxArch(module: String, version: String? = null): Any =
        androidx("arch.core", "core-$module", version)

/**
 * Builds the dependency notation for the named Androidx lifecycle at the given [version].
 *
 * @param module simple name of the Androidx lifecycle module, for example "livedata".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxLifecycle(module: String, version: String? = null): Any =
        androidx("lifecycle", "lifecycle-$module", version)

/**
 * Builds the dependency notation for the named Androidx paging at the given [version].
 *
 * @param module simple name of the Androidx paging module, for example "runtime".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxPaging(module: String, version: String? = null): Any =
        androidx("paging", "paging-$module", version)

/**
 * Builds the dependency notation for the named Androidx Room at the given [version].
 *
 * @param module simple name of the Androidx Room module, for example "runtime".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxRoom(module: String, version: String? = null): Any =
        androidx("room", "room-$module", version)

/**
 * Builds the dependency notation for the named Androidx-test at the given [version].
 *
 * @param module simple name of the Androidx-test module, for example "runner".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxTest(module: String, version: String? = null): Any =
        androidx("test", module, version)

/**
 * Builds the dependency notation for the named Androidx-work at the given [version].
 *
 * @param module simple name of the Androidx-work module, for example "runtime".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxWork(module: String, version: String? = null): Any =
        androidx("work", "work-$module", version)
// endregion
// endregion

// region Square
/**
 * Builds the dependency notation for Retrofit 2.
 * @see squareup
 *
 * You can also use `` `retrofit` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`retrofit` get() = squareup("retrofit2", "retrofit", version = `retrofit version`)


var `retrofit version` = defaultRetrofitVersion
// endregion

// region 4face
/**
 * Builds the dependency notation for FluentNotifications.
 * @see forface
 *
 * You can also use `` `fluentNotifications` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`fluentNotifications` get() = forface("fluentnotifications", version = `fluentNotifications version`)


var `fluentNotifications version` = defaultFluentNotificationsVersion


/**
 * Builds the dependency notation for ViewStateStore.
 * @see forface
 *
 * You can also use `` `viewStateStore` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`viewStateStore` get() = forface("viewstatestore", version = `viewStateStore version`)

/**
 * Builds the dependency notation for ViewStateStore-paging.
 * @see forface
 *
 * You can also use `` `viewStateStore-paging` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`viewStateStore-paging` get() = forface("viewstatestore", module = "viewstatestore-paging", version = `viewStateStore version`)


var `viewStateStore version` = defaultViewStateStoreVersion


/**
 * Builds the dependency notation for the named `studio.forface` group at the given [version].
 *
 * @param groupName simple name of the `studio.forface` group, for example "viewStateStore"
 * @param module simple name of the `studio.forface` module, for example "viewStateStore-paging". Default is [groupName]
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.forface(groupName: String, module: String = groupName, version: String? = null): Any =
        "studio.forface.$groupName:$module${version?.let { ":$version" } ?: "" }"
// endregion