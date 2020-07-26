[dsl](../index.md) / [studio.forface.easygradle.dsl](index.md) / [property](./property.md)

# property

`fun <T> property(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<Project, T?>`

### Parameters

`name` - of the property. If none is specified [KProperty.name](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-callable/name.html) will be used

**Return**
[ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html) for delegate a variable

**See Also**

[Project.findProperty](#)

[Project.setProperty](#)

