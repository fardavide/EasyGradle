[plugin](../../index.md) / [studio.forface.easygradle.internal](../index.md) / [ConfigReadWriteProperty](./index.md)

# ConfigReadWriteProperty

`abstract class ConfigReadWriteProperty<Scope : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, PropType : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<Scope, PropType>`

[ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html) for delegate to variables of Configuration classes

**Author**
Davide Farella

### Constructors

| [&lt;init&gt;](-init-.md) | [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html) for delegate to variables of Configuration classes`ConfigReadWriteProperty(project: Project, default: PropType, propertyName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, propertyPrefix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", envName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null)` |

### Functions

| [getValue](get-value.md) | `open fun getValue(thisRef: Scope, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): PropType` |
| [setValue](set-value.md) | `open fun setValue(thisRef: Scope, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>, value: PropType): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toList](to-list.md) | `open fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.toList(property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): PropType?` |

