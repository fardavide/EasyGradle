[dsl](../index.md) / [studio.forface.easygradle.dsl](./index.md)

## Package studio.forface.easygradle.dsl

### Types

| [DokkaConfig](-dokka-config.md) | `typealias DokkaConfig = DokkaTask.(Project) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [OutputFormat](-output-format/index.md) | `enum class OutputFormat` |
| [PublishConfig](-publish-config/index.md) | Holds params for publication. Each parameter can be set`class PublishConfig` |
| [PublishConfigBuilder](-publish-config-builder.md) | Lambda for build a [PublishConfig](-publish-config/index.md) within a [Project](#)`typealias PublishConfigBuilder = `[`PublishConfig`](-publish-config/index.md)`.(Project) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [Version](-version/index.md) | Class for libraries and applications versioning. Compatible with Android`class Version` |

### Extensions for External Classes

| [kotlin.Any](kotlin.-any/index.md) |  |
| [org.gradle.api.artifacts.Dependency](org.gradle.api.artifacts.-dependency/index.md) |  |
| [org.gradle.api.artifacts.dsl.DependencyHandler](org.gradle.api.artifacts.dsl.-dependency-handler/index.md) |  |
| [org.gradle.api.Project](org.gradle.api.-project/index.md) |  |
| [org.gradle.plugin.use.PluginDependenciesSpec](org.gradle.plugin.use.-plugin-dependencies-spec/index.md) |  |
| [org.jetbrains.dokka.gradle.DokkaTask](org.jetbrains.dokka.gradle.-dokka-task/index.md) |  |

### Properties

| [coroutines version](coroutines version.md) | `var coroutines version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [dagger version](dagger version.md) | `var dagger version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [detect-code-analysis version](detect-code-analysis version.md) | `var detect-code-analysis version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [detekt version](detekt version.md) | `var detekt version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [kotlin version](kotlin version.md) | `var kotlin version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [kotlinPoet version](kotlin-poet version.md) | `var kotlinPoet version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ktor version](ktor version.md) | `var ktor version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [mockK version](mock-k version.md) | `var mockK version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [serialization version](serialization version.md) | `var serialization version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [sqlDelight version](sql-delight version.md) | `var sqlDelight version: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [version](version.md) | `var `[`PublishConfig`](-publish-config/index.md)`.version: `[`Version`](-version/index.md) |

### Functions

| [DokkaConfig](-dokka-config.md) | Create a base [DokkaConfig](-dokka-config.md) that can be used later`fun DokkaConfig(builder: `[`DokkaConfig`](-dokka-config.md)`): `[`DokkaConfig`](-dokka-config.md) |
| [property](property.md) | `fun <T> property(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<Project, T?>` |
| [PublishConfig](-publish-config.md) | `fun PublishConfig(block: `[`PublishConfigBuilder`](-publish-config-builder.md)`): `[`PublishConfigBuilder`](-publish-config-builder.md) |

