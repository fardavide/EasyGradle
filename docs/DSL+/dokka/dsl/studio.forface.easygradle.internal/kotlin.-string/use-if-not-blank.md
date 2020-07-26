[dsl](../../index.md) / [studio.forface.easygradle.internal](../index.md) / [kotlin.String](index.md) / [useIfNotBlank](./use-if-not-blank.md)

# useIfNotBlank

`fun `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`.useIfNotBlank(block: (`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

**Return**
[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)
If receiver [String.isNotBlank](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/is-not-blank.html) is not null, use it for create another string, else return an empty string

