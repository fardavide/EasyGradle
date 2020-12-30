@file:Suppress("unused", "PackageDirectoryMismatch", "MaxLineLength", "DeprecatedCallableAddReplaceWith")

package studio.forface.easygradle.dsl.android

import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.dsl.*

// region 4face
@Deprecated(StyleMessage, ReplaceWith("fluentNotifications()"))
val DependencyHandler.`fluentNotifications` get() = forface("fluentnotifications") version `fluentNotifications version`

@Deprecated(StyleMessage, ReplaceWith("theia()"))
val DependencyHandler.`theia` get() = forface("theia") version `theia version`

@Deprecated(StyleMessage, ReplaceWith("viewStateStore()"))
val DependencyHandler.`viewStateStore` get() = forface("viewstatestore") version `viewStateStore version`
@Deprecated(StyleMessage, ReplaceWith("viewStateStore(\"paging\")"))
val DependencyHandler.`viewStateStore-paging` get() = forface("viewstatestore", moduleSuffix = "paging") version `viewStateStore version`
// endregion

// region Jetpack

// region Arch
@Deprecated(StyleMessage, ReplaceWith("androidArch(\"common\")"))
val DependencyHandler.`android-arch-common` get() = androidArch("common")
@Deprecated(StyleMessage, ReplaceWith("androidArch(\"runtime\")"))
val DependencyHandler.`android-arch-runtime` get() = androidArch("runtime")
@Deprecated(StyleMessage, ReplaceWith("androidArch(\"testing\")"))
val DependencyHandler.`android-arch-testing` get() = androidArch("testing")
// endregion

// region Lifecycle
@Deprecated(StyleMessage, ReplaceWith("lifecycle(\"compiler\")"))
val DependencyHandler.`lifecycle-compiler` get() = lifecycle("compiler")
@Deprecated(StyleMessage, ReplaceWith("lifecycle(\"extensions\")"))
val DependencyHandler.`lifecycle-extensions` get() = lifecycle("extensions")
@Deprecated(StyleMessage, ReplaceWith("lifecycle(\"liveData\")"))
val DependencyHandler.`lifecycle-liveData` get() = lifecycle("livedata-ktx")
@Deprecated(StyleMessage, ReplaceWith("lifecycle(\"runtime\")"))
val DependencyHandler.`lifecycle-runtime` get() = lifecycle("runtime-ktx")
@Deprecated(StyleMessage, ReplaceWith("lifecycle(\"viewModel\")"))
val DependencyHandler.`lifecycle-viewModel` get() = lifecycle("viewmodel-ktx")
// endregion

// region Paging
@Deprecated(StyleMessage, ReplaceWith("paging(\"common\")"))
val DependencyHandler.`paging-common` get() = paging("common-ktx")
@Deprecated(StyleMessage, ReplaceWith("paging(\"runtime\")"))
val DependencyHandler.`paging-runtime` get() = paging("runtime-ktx")
// endregion

// region Room
@Deprecated(StyleMessage, ReplaceWith("room(\"runtime\")"))
val DependencyHandler.`room-runtime` get() = androidxRoom("runtime")
@Deprecated(StyleMessage, ReplaceWith("room(\"compiler\")"))
val DependencyHandler.`room-compiler` get() = androidxRoom("compiler")
@Deprecated(StyleMessage, ReplaceWith("room(\"ktx\")"))
val DependencyHandler.`room-ktx` get() = androidxRoom("ktx")
@Deprecated(StyleMessage, ReplaceWith("room(\"testing\")"))
val DependencyHandler.`room-testing` get() = androidxRoom("testing")
// endregion

// region Test
@Deprecated(StyleMessage, ReplaceWith("androidTest(\"core\")"))
val DependencyHandler.`android-test-core` get() = androidxTest("core")
@Deprecated(StyleMessage, ReplaceWith("androidTest(\"junit\")"))
val DependencyHandler.`android-test-junit` get() = androidx("test.ext", "junit") version `android-test version`
@Deprecated(StyleMessage, ReplaceWith("androidTest(\"rules\")"))
val DependencyHandler.`android-test-rules` get() = androidxTest("rules")
@Deprecated(StyleMessage, ReplaceWith("androidTest(\"runner\")"))
val DependencyHandler.`android-test-runner` get() = androidxTest("runner")
// endregion

// region Work
@Deprecated(StyleMessage, ReplaceWith("androidWork(\"runtime\")"))
val DependencyHandler.`android-work-runtime` get() = androidxWork("runtime-ktx")
@Deprecated(StyleMessage, ReplaceWith("androidWork(\"testing\")"))
val DependencyHandler.`android-work-testing` get() = androidxWork("testing")
// endregion

// region other
@Deprecated(PluginMessage)
val DependencyHandler.`android-gradle-plugin` get() = android("tools.build", "gradle")


@Deprecated(StyleMessage, ReplaceWith("activity()"))
val DependencyHandler.`activity` get() = androidx("activity", moduleSuffix = "ktx") version `activity version`

@Deprecated(StyleMessage, ReplaceWith("androidAnnotation()"))
val DependencyHandler.`android-annotation` get() = androidx("annotation") version `android-annotation version`

@Deprecated(StyleMessage, ReplaceWith("androidKtx()"))
val DependencyHandler.`android-ktx` get() = androidx("core", moduleSuffix = "ktx") version `ktx version`

@Deprecated(StyleMessage, ReplaceWith("appCompat()"))
val DependencyHandler.`appcompat` get() = androidx("appcompat") version `appcompat version`

@Deprecated(StyleMessage, ReplaceWith("constraintLayout()"))
val DependencyHandler.`constraint-layout` get() = androidx("constraintlayout") version `constraint-layout version`

@Deprecated(StyleMessage, ReplaceWith("espresso()"))
val DependencyHandler.`espresso` get() = androidx("test.espresso", "espresso-core") version `espresso version`

@Deprecated(StyleMessage, ReplaceWith("fragment()"))
val DependencyHandler.`fragment` get() = androidx("fragment", moduleSuffix = "ktx") version `fragment version`

@Deprecated(StyleMessage, ReplaceWith("material()"))
val DependencyHandler.`material` get() = googleAndroid("material") version `material version`

@Deprecated(StyleMessage, ReplaceWith("robolectric()"))
val DependencyHandler.`robolectric` get() = dependency("org.robolectric", module = "robolectric") version `robolectric version`
// endregion
// endregion

// region Google
@Deprecated(StyleMessage, ReplaceWith("daggerAndroid()"))
val DependencyHandler.`dagger-android` get() = dagger("android")
@Deprecated(StyleMessage, ReplaceWith("daggerAndroid(\"support\")"))
val DependencyHandler.`dagger-android-support` get() = dagger("android-support")
@Deprecated(StyleMessage, ReplaceWith("daggerAndroid(\"processor\")"))
val DependencyHandler.`dagger-android-processor` get() = dagger("android-processor")

@Deprecated(StyleMessage, ReplaceWith("hiltAndroid()"))
val DependencyHandler.`hilt-android` get() = hiltAndroid()
@Deprecated(StyleMessage, ReplaceWith("hiltAndroid(\"compiler\")"))
val DependencyHandler.`hilt-android-compiler` get() = hiltAndroid("compiler")
@Deprecated(StyleMessage, ReplaceWith("hiltAndroid(\"testing\")"))
val DependencyHandler.`hilt-android-testing` get() = hiltAndroid("testing")

@Deprecated(PluginMessage)
val DependencyHandler.`hilt-android-gradle-plugin` get() = hiltAndroid("gradle-plugin")


@Deprecated(StyleMessage, ReplaceWith("hiltAndroidx(\"annotations\")"))
val DependencyHandler.`hilt-androidx-annotations` get() = hiltAndroidx("common")
@Deprecated(StyleMessage, ReplaceWith("hiltAndroidx(\"compiler\")"))
val DependencyHandler.`hilt-androidx-compiler` get() = hiltAndroidx("compiler")
@Deprecated(StyleMessage, ReplaceWith("hiltAndroidx(\"viewModel\")"))
val DependencyHandler.`hilt-androidx-viewModel` get() = hiltAndroidx("lifecycle-viewmodel")
@Deprecated(StyleMessage, ReplaceWith("hiltAndroidx(\"workManager\")"))
val DependencyHandler.`hilt-androidx-workManager` get() = hiltAndroidx("work")

// endregion

// region Square
@Deprecated(StyleMessage, ReplaceWith("retrofit()"))
val DependencyHandler.`retrofit` get() = squareup("retrofit2", "retrofit") version `retrofit version`
// endregion

// region Jake Wharton
@Deprecated(StyleMessage, ReplaceWith("retrofitKotlinSerialization()"))
val DependencyHandler.`retrofit-kotlin-serialization` get() = jakeWharton("retrofit", "retrofit2-kotlinx-serialization-converter") version `retrofit-kotlin-serialization version`

@Deprecated(StyleMessage, ReplaceWith("timber()"))
val DependencyHandler.`timber` get() = jakeWharton("timber") version `timber version`
// endregion
