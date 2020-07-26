[plugin](../../index.md) / [studio.forface.easygradle.internal](../index.md) / [ConfigReadWriteProperty](index.md) / [toList](./to-list.md)

# toList

`protected open fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.toList(property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): PropType?`

### Parameters

`property` - [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html) which will be used for match the type;
e.g. ` if(property == SomeClass::property) { Json.parseList... } `

**Return**
[PropType](index.md#PropType) which is a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)

**Receiver**
[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) must be in Json format

