[dsl](../../index.md) / [studio.forface.easygradle.dsl](../index.md) / [Version](./index.md)

# Version

`class Version`

Class for libraries and applications versioning. Compatible with Android

**Author**
Davide Farella

### Types

| [Channel](-channel/index.md) | A sealed class for the Channel of the Version of the Module`sealed class Channel` |

### Constructors

| [&lt;init&gt;](-init-.md) | `Version(major: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, minor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, patch: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>Class for libraries and applications versioning. Compatible with Android`Version(major: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, minor: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, channel: Channel = None, patch: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = if (channel is None) 0 else 1, build: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)` |

### Properties

| [versionCode](version-code.md) | `val versionCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [versionName](version-name.md) | `val versionName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

