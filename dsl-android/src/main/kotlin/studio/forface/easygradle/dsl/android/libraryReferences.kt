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
val DependencyHandler.`android-ktx` get() = androidx("core", "core-ktx")

/**
 * Builds the dependency notation for Androidx-test-core.
 * @see androidxTest
 *
 * You can also use `` `android-test-core` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-test-core` get() = androidxTest("core")

/**
 * Builds the dependency notation for Androidx-test-junit.
 * @see androidx
 *
 * You can also use `` `android-test-junit` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-test-junit` get() = androidx("test.ext", "junit")

/**
 * Builds the dependency notation for Androidx-test-rules.
 * @see androidxTest
 *
 * You can also use `` `android-test-rules` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-test-rules` get() = androidxTest("rules")

/**
 * Builds the dependency notation for Androidx-test-runner.
 * @see androidxTest
 *
 * You can also use `` `android-test-runner` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`android-test-runner` get() = androidxTest("runner")

/**
 * Builds the dependency notation for Annotation.
 * @see androidx
 *
 * You can also use `` `annotation` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`annotation` get() = androidx("annotation")

/**
 * Builds the dependency notation for AppCompat.
 * @see androidx
 *
 * You can also use `` `appcompat` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`appcompat` get() = androidx("appcompat")

/**
 * Builds the dependency notation for ConstraintLayout.
 * @see androidx
 *
 * You can also use `` `constraint-layout` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`constraint-layout` get() = androidx("constraintlayout")

/**
 * Builds the dependency notation for Espresso-core.
 * @see androidx
 *
 * You can also use `` `espresso` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`espresso` get() = androidx("test.espresso", "espresso-core")

/**
 * Builds the dependency notation for Lifecycle-compiler.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-compiler` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-compiler` get() = androidxLifecycle("compiler")

/**
 * Builds the dependency notation for Lifecycle-extensions.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-extensions` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-extensions` get() = androidxLifecycle("extensions")

/**
 * Builds the dependency notation for Lifecycle-livedata.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-liveData` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-liveData` get() = androidxLifecycle("livedata-ktx")

/**
 * Builds the dependency notation for Lifecycle-runtime.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-runtime` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-runtime` get() = androidxLifecycle("runtime-ktx")

/**
 * Builds the dependency notation for Lifecycle-viewmodel.
 * @see androidxLifecycle
 *
 * You can also use `` `lifecycle-viewModel` version "1.0.0" `` if you want to use an explicit version.
 */
val DependencyHandler.`lifecycle-viewModel` get() = androidxLifecycle("viewmodel-ktx")

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
 * Builds the dependency notation for the named Androidx-lifecycle at the given [version].
 *
 * @param module simple name of the Androidx-lifecycle module, for example "livedata".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxLifecycle(module: String, version: String? = null): Any =
        androidx("lifecycle", "lifecycle-$module", version)

/**
 * Builds the dependency notation for the named Androidx-test at the given [version].
 *
 * @param module simple name of the Androidx-test module, for example "runner".
 * @param version optional desired version, unspecified if null.
 */
fun DependencyHandler.androidxTest(module: String, version: String? = null): Any =
        androidx("test", module, version)