package studio.forface.easygradle.dsl.internal

import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaGetter

/**
 * Assert that all of the given [KProperty]s do not refer to an empty string
 *
 * @throws IllegalArgumentException
 * @see paramNotSet
 */
internal fun Any.assertStringsNotEmpty(vararg prop: KProperty<String>) = prop.forEach(::assertStringNotEmpty)

/**
 * Assert that the given [KProperty] does not refer to an empty string
 *
 * @throws IllegalArgumentException
 * @see paramNotSet
 */
internal fun Any.assertStringNotEmpty(prop: KProperty<String>) {
    val string = prop.javaGetter!!.invoke(this) as String
    if (string.isEmpty()) throw paramNotSet(this::class, prop)
}

/** @throws IllegalArgumentException */
private fun paramNotSet(kclass: KClass<*>, prop: KProperty<*>) =
        IllegalArgumentException("`${kclass.simpleName}.${prop.name}` has not being set")

/**
 * @return [String]
 * If receiver [String] is not null, use it for create another string, else return an empty string
 */
fun String?.useIfNotNull(block: (String) -> String) = this?.let(block) ?: ""
