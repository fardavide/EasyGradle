@file:Suppress("unused", "MaxLineLength", "PackageDirectoryMismatch", "DeprecatedCallableAddReplaceWith")
package studio.forface.easygradle.dsl

import org.gradle.api.artifacts.dsl.DependencyHandler

// region 4face
@Deprecated(StyleMessage, ReplaceWith("assert4k()"))
val DependencyHandler.`assert4k` get() = forface(module = "assert4k") version `assert4k version`
// endregion

// region Kotlin
// region StdLib
@Deprecated(NotNeededMessage)
val DependencyHandler.`kotlin` get() = kotlin("stdlib")
@Deprecated(NotNeededMessage)
val DependencyHandler.`kotlin-common` get() = kotlin("stdlib-common")
@Deprecated(NotNeededMessage)
val DependencyHandler.`kotlin-jdk7` get() = kotlin("stdlib-jdk7")
@Deprecated(NotNeededMessage)
val DependencyHandler.`kotlin-jdk8` get() = kotlin("stdlib-jdk8")
@Deprecated(NotNeededMessage)
val DependencyHandler.`kotlin-js` get() = kotlin("stdlib-js")
@Deprecated(StyleMessage, ReplaceWith("kotlin(\"kapt\")"))
val DependencyHandler.`kotlin-kapt` get() = kotlin("kapt")
@Deprecated(StyleMessage, ReplaceWith("kotlin(\"reflect\")"))
val DependencyHandler.`kotlin-reflect` get() = kotlin("reflect")
@Deprecated(StyleMessage, ReplaceWith("kotlin(\"test\")"))
val DependencyHandler.`kotlin-test` get() = kotlin("test")
@Deprecated(NotNeededMessage)
val DependencyHandler.`kotlin-test-common` get() = kotlin("test-common")
@Deprecated(NotNeededMessage)
val DependencyHandler.`kotlin-test-js` get() = kotlin("test-js")
@Deprecated(StyleMessage, ReplaceWith("kotlin(\"test-junit\")"))
val DependencyHandler.`kotlin-test-junit` get() = kotlin("test-junit")
@Deprecated(StyleMessage, ReplaceWith("kotlin(\"test-junit5\")"))
val DependencyHandler.`kotlin-test-junit5` get() = kotlin("test-junit5")
@Deprecated(PluginMessage)
val DependencyHandler.`kotlin-gradle-plugin` get() = kotlin("gradle-plugin")
// endregion

// region Coroutines
@Deprecated(StyleMessage, ReplaceWith("coroutines(\"android\")"))
val DependencyHandler.`coroutines-android` get() = coroutines("android")
@Deprecated(StyleMessage, ReplaceWith("coroutines()"))
val DependencyHandler.`coroutines-core` get() = coroutines("core")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-common` get() = coroutines("core-common")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-iosx64` get() = coroutines("core-iosx64")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-iosarm64` get() = coroutines("core-iosarm64")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-iosarm32` get() = coroutines("core-iosarm32")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-linuxx64` get() = coroutines("core-linuxx64")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-macosx64` get() = coroutines("core-macosx64")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-native` get() = coroutines("core-native")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-js` get() = coroutines("core-js")
@Deprecated(NotNeededMessage)
val DependencyHandler.`coroutines-core-windowsx64` get() = coroutines("core-windowsx64")
val DependencyHandler.`coroutines-jdk8` get() = coroutines("jdk8")
@Deprecated(StyleMessage, ReplaceWith("coroutines(\"test\")"))
val DependencyHandler.`coroutines-test` get() = coroutines("test")
// endregion

// region Serialization
@Deprecated(StyleMessage, ReplaceWith("serialization(\"json\")"))
val DependencyHandler.`serialization-json` get() = serialization("json")

@Deprecated(PluginMessage)
val DependencyHandler.`serialization-gradle-plugin` get() = kotlin("serialization")
// endregion

// endregion

// region Google

// region Dagger
@Deprecated(StyleMessage, ReplaceWith("dagger()"))
val DependencyHandler.`dagger` get() = dagger()
@Deprecated(StyleMessage, ReplaceWith("dagger(\"compile\")"))
val DependencyHandler.`dagger-compiler` get() = dagger("compiler")

// endregion
// endregion

// region Square
@Deprecated(StyleMessage, ReplaceWith("assistedInject(\"annotations-dagger2\")"))
val DependencyHandler.`assistedInject-annotations-dagger` get() = squareup("inject", "assisted-inject-annotations-dagger2") version `assistedInject version`
@Deprecated(StyleMessage, ReplaceWith("assistedInject(\"processor-dagger2\")"))
val DependencyHandler.`assistedInject-processor-dagger` get() = squareup("inject", "assisted-inject-processor-dagger2") version `assistedInject version`


@Deprecated(StyleMessage, ReplaceWith("kotlinpoet()"))
val DependencyHandler.`kotlinPoet` get() = squareup("kotlinpoet") version `kotlinPoet version`
@Deprecated(StyleMessage, ReplaceWith("kotlinpoet(\"metadata-specs\")"))
val DependencyHandler.`kotlinPoet-metadata-specs` get() = squareup("kotlinpoet", moduleSuffix = "metadata-specs") version `kotlinPoet version`

@Deprecated(StyleMessage, ReplaceWith("sqlDelight(\"android-driver\")"))
val DependencyHandler.`sqlDelight-android-driver` get() = sqlDelight("android-driver")
@Deprecated(StyleMessage, ReplaceWith("sqlDelight(\"native-driver\")"))
val DependencyHandler.`sqlDelight-native-driver` get() = sqlDelight("native-driver")
@Deprecated(StyleMessage, ReplaceWith("sqlDelight(\"sqlite-driver\")"))
val DependencyHandler.`sqlDelight-sqlite-driver` get() = sqlDelight("sqlite-driver")
@Deprecated(StyleMessage, ReplaceWith("sqlDelight(\"sqljs-driver\")"))
val DependencyHandler.`sqlDelight-sqljs-driver` get() = sqlDelight("sqljs-driver")

@Deprecated(PluginMessage)
val DependencyHandler.`sqlDelight-gradle-plugin` get() = sqlDelight("gradle-plugin")

// endregion

// region Detekt
@Deprecated(StyleMessage, ReplaceWith("detekt()"))
val DependencyHandler.`detekt-cli` get() = detekt("cli")
@Deprecated(StyleMessage, ReplaceWith("detekt(\"formatting\")"))
val DependencyHandler.`detekt-formatting` get() = detekt("formatting")
@Deprecated(StyleMessage, ReplaceWith("detektCodeAnalysis()"))
val DependencyHandler.`detekt-code-analysis` get() = dependency("pm.algirdas", "detekt", "codeanalysis") version `detekt-code-analysis version`
// endregion

// region MockK
@Deprecated(StyleMessage, ReplaceWith("mockk()"))
val DependencyHandler.`mockk` get() = mockK() version `mockK version`
@Deprecated(StyleMessage, ReplaceWith("mockk(\"android\")"))
val DependencyHandler.`mockk-android` get() = mockK("android") version `mockK version`

// endregion

const val StyleMessage = "Use function style"
const val NotNeededMessage = "Not needed in Kotlin 1.4"
const val PluginMessage = "Classpath references are not provided anymore, as plugin block style is suggested"
