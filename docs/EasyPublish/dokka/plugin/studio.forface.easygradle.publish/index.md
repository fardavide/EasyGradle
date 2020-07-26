[plugin](../index.md) / [studio.forface.easygradle.publish](./index.md)

## Package studio.forface.easygradle.publish

### Types

| [EasyPublishExtension](-easy-publish-extension/index.md) | Holds params for publication. Each parameter can be set`abstract class EasyPublishExtension` |
| [EasyPublishPlugin](-easy-publish-plugin/index.md) | `abstract class EasyPublishPlugin : Plugin<Project>` |
| [PublishConfigBuilder](-publish-config-builder.md) | Lambda for build a [EasyPublishExtension](-easy-publish-extension/index.md) within a [Project](#)`typealias PublishConfigBuilder = `[`EasyPublishExtension`](-easy-publish-extension/index.md)`.(Project) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extensions for External Classes

| [org.gradle.api.Project](org.gradle.api.-project/index.md) |  |

### Properties

| [EXTENSION_NAME](-e-x-t-e-n-s-i-o-n_-n-a-m-e.md) | `const val EXTENSION_NAME: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [version](version.md) | `var `[`EasyPublishExtension`](-easy-publish-extension/index.md)`.version: Version` |

### Functions

| [PublishConfig](-publish-config.md) | `fun PublishConfig(block: `[`PublishConfigBuilder`](-publish-config-builder.md)`): `[`PublishConfigBuilder`](-publish-config-builder.md) |

