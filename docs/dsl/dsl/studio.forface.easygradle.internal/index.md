[dsl](../index.md) / [studio.forface.easygradle.internal](./index.md)

## Package studio.forface.easygradle.internal

### Types

| [WriteOnlyProperty](-write-only-property/index.md) | [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html) that throws [UnsupportedOperationException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unsupported-operation-exception/index.html) on getter`abstract class WriteOnlyProperty<T, V> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<T, V>` |

### Functions

| [lateinit](lateinit.md) | This works as a lateinit but show a more detailed exception if the property has not been set. It's type is [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) and will be used for versions for accessors`fun lateinit(): `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [writeOnly](write-only.md) | Function for delegate to [WriteOnlyProperty](-write-only-property/index.md)`fun <T, V> writeOnly(setter: T.(V) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`WriteOnlyProperty`](-write-only-property/index.md)`<T, V>` |

