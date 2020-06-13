[dsl](../../index.md) / [studio.forface.easygradle.dsl](../index.md) / [PublishConfig](./index.md)

# PublishConfig

`class PublishConfig`

Holds params for publication.
Each parameter can be set

* programmatically
* in `gradle.properties`
* as Environment variable
properties names in gradle.properties reflect the field name of the variable and environment variables reflect the
property name name as uppercase snake_case where not specified differently.
See gradle.properties.template for more examples

### Types

| [Developer](-developer/index.md) | `class Developer` |
| [DevelopersBuilder](-developers-builder.md) | Scope for [DevelopersBuilder.developer](developer.md)`class DevelopersBuilder` |
| [License](-license/index.md) | `class License` |
| [LicensesBuilder](-licenses-builder.md) | Scope for [LicensesBuilder.license](license.md)`class LicensesBuilder` |

### Annotations

| [Marker](-marker/index.md) | `annotation class Marker` |

### Properties

| [apiKey](api-key.md) | Api Key of Bintray user. Property name: `bintray.apikey``var apiKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [artifact](artifact.md) | Name of the artifact to public. Default is [Project.getName](#)`var artifact: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!` |
| [description](description.md) | Optional description of the library`var description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [group](group.md) | Group of the Library`var group: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [name](name.md) | Name of the project on Bintray. Default is `$group:$artifact` Property name: `projectName``var name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [organization](organization.md) | Optional name of the organization`var organization: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [override](override.md) | Whether the publication must override a pre-existent one Default is `false` Property name: `bintray.override``var override: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [publish](publish.md) | Whether the publication must be published Default is `false` Property name: `bintray.publish``var publish: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [repo](repo.md) | Name of the Repository where to publish`var repo: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [scmConnection](scm-connection.md) | Scm connection of the library Property name: `scm.connection``var scmConnection: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [scmDevConnection](scm-dev-connection.md) | Scm Dev connection of the library Property name: `scm.devConnection``var scmDevConnection: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [scmUrl](scm-url.md) | Scm url of the library Property name: `scm.url``var scmUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [siteUrl](site-url.md) | Optional website url of the library`var siteUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [username](username.md) | Username of Bintray user. Property name: `bintray.user``var username: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [versionName](version-name.md) | Version of the library Property name: `version``var versionName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| [developer](developer.md) | `fun developer(block: Developer.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): Developer`<br>Add a set of [Developer](-developer/index.md)s to the current [PublishConfig](./index.md)`fun DevelopersBuilder.developer(block: Developer.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [developers](developers.md) | Create and add a [License](-license/index.md) to the current [PublishConfig](./index.md)`fun developers(block: DevelopersBuilder.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [license](license.md) | `fun license(block: License.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): License`<br>Create and add a [License](-license/index.md) to the current [PublishConfig](./index.md)`fun LicensesBuilder.license(block: License.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [licenses](licenses.md) | Add a set of [License](-license/index.md)s to the current [PublishConfig](./index.md)`fun licenses(block: LicensesBuilder.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [unaryPlus](unary-plus.md) | Add receiver [License](-license/index.md) to [PublishConfig.licenses](licenses.md)`operator fun License.unaryPlus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add receiver [Developer](-developer/index.md) to [PublishConfig.devs](#)`operator fun Developer.unaryPlus(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extension Properties

| [version](../version.md) | `var `[`PublishConfig`](./index.md)`.version: `[`Version`](../-version/index.md) |

