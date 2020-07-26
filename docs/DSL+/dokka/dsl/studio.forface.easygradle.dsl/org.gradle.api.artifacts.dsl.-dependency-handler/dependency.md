[dsl](../../index.md) / [studio.forface.easygradle.dsl](../index.md) / [org.gradle.api.artifacts.dsl.DependencyHandler](index.md) / [dependency](./dependency.md)

# dependency

`fun DependencyHandler.dependency(group: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, groupName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, module: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, moduleSuffix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)

Build a dependency notation

### Parameters

`group` - the partial group of the dependency. e.g. ` studio.forface `

`groupName` - optional name of the group. e.g. ` viewstatestore ` for `studio.forface.viewstatestore`

`module` - module of the dependency, if not set [groupName](dependency.md#studio.forface.easygradle.dsl$dependency(org.gradle.api.artifacts.dsl.DependencyHandler, kotlin.String, kotlin.String, kotlin.String, kotlin.String, kotlin.String)/groupName) will be used. e.g. ` dagger ` for `com.google.dagger`

`moduleSuffix` - optional suffix for the [module](dependency.md#studio.forface.easygradle.dsl$dependency(org.gradle.api.artifacts.dsl.DependencyHandler, kotlin.String, kotlin.String, kotlin.String, kotlin.String, kotlin.String)/module). e.g. ` ktx ` for `core-ktx`

`version` - optional version for the dependency

### Exceptions

`IllegalArgumentException` - if none of [groupName](dependency.md#studio.forface.easygradle.dsl$dependency(org.gradle.api.artifacts.dsl.DependencyHandler, kotlin.String, kotlin.String, kotlin.String, kotlin.String, kotlin.String)/groupName) and [module](dependency.md#studio.forface.easygradle.dsl$dependency(org.gradle.api.artifacts.dsl.DependencyHandler, kotlin.String, kotlin.String, kotlin.String, kotlin.String, kotlin.String)/module) is set

**Return**
[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)

