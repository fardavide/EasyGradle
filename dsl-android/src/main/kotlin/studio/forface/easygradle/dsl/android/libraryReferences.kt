@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)
package studio.forface.easygradle.dsl.android

import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Builds the dependency notation for Core-ktx.
 * @see androidx
 *
 * You can also use `` `android-ktx` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-ktx` get() = androidx("core", "core-ktx", version = `ktx version`)

var `ktx version` = defaultKtxVersion

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

/**
 * Builds the dependency notation for Annotation.
 * @see androidx
 *
 * You can also use `` `annotation` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-annotation` get() = androidx("annotation", version = `android-annotation version`)

var `android-annotation version` = defaultAnnotationVersion

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

/**
 * Builds the dependency notation for AppCompat.
 * @see androidx
 *
 * You can also use `` `appcompat` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`appcompat` get() = androidx("appcompat", version = `appcompat version`)

var `appcompat version` = defaultSupportVersion

/**
 * Builds the dependency notation for ConstraintLayout.
 * @see androidx
 *
 * You can also use `` `constraint-layout` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`constraint-layout` get() = androidx("constraintlayout", version = `constraint-layout version`)

var `constraint-layout version` = defaultConstraintLayoutVersion

/**
 * Builds the dependency notation for Espresso-core.
 * @see androidx
 *
 * You can also use `` `espresso` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`espresso` get() = androidx("test.espresso", "espresso-core", version = `espresso version`)

var `espresso version` = defaultEspressoVersion

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

/**
 * Builds the dependency notation for Paging-common.
 * @see androidxLifecycle
 *
 * You can also use `` `paging-common` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`paging-common` get() = androidxPaging("common-ktx", version = `android-paging version`)

/**
 * Builds the dependency notation for Paging-runtime.
 * @see androidxLifecycle
 *
 * You can also use `` `paging-runtime` version "2.1.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`paging-runtime` get() = androidxPaging("runtime-ktx", version = `android-paging version`)

var `android-paging version` = defaultPagingVersion

/**
 * Builds the dependency notation for Material.
 * @see googleAndroid
 *
 * You can also use `` `material` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`material` get() = googleAndroid("material", version = `material version`)

var `material version` = defaultMaterialVersion


/**
 * Builds the dependency notation for Android Gradle plugin.
 * @see android
 *
 * You can also use `` `android-gradle-plugin` version "3.5.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-gradle-plugin` get() = android("tools.build", module = "gradle", version = `android-gradle-plugin version`)

var `android-gradle-plugin version` = defaultAGPVersion


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
 * Builds the dependency notation for the named Androidx-test at the given [version].
 *
 * @param module simple name of the Androidx-test module, for example "runner".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxTest(module: String, version: String? = null): Any =
        androidx("test", module, version)