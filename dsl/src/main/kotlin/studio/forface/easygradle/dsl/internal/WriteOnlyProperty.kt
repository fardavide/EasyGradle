package studio.forface.easygradle.dsl.internal

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/** Function for delegate to [WriteOnlyProperty] */
internal fun <T> writeOnly(setter: (T) -> Unit) = object : WriteOnlyProperty<T>(setter) {}

/**
 * [ReadWriteProperty] that throws [UnsupportedOperationException] on getter
 * @author Davide Farella
 */
internal abstract class WriteOnlyProperty<T>(
        val setter: (T) -> Unit
) : ReadWriteProperty<Any?, T> {

    /** @throws UnsupportedOperationException */
    override fun getValue(thisRef: Any?, property: KProperty<*>) =
            throw UnsupportedOperationException("Property '${property.name} doesn't support get method'")

    /** @see ReadWriteProperty.setValue */
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setter(value)
    }
}