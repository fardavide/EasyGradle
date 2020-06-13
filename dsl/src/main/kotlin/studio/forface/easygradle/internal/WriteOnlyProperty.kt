package studio.forface.easygradle.internal

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/** Function for delegate to [WriteOnlyProperty] */
fun <T, V> writeOnly(setter: T.(V) -> Unit) = object : WriteOnlyProperty<T, V>(setter) {}

/**
 * [ReadWriteProperty] that throws [UnsupportedOperationException] on getter
 * @author Davide Farella
 */
abstract class WriteOnlyProperty<T, V>(
    val setter: T.(V) -> Unit
) : ReadWriteProperty<T, V> {

    /** @throws UnsupportedOperationException */
    override fun getValue(thisRef: T, property: KProperty<*>) =
            throw UnsupportedOperationException("Property '${property.name} doesn't support get method'")

    /** @see ReadWriteProperty.setValue */
    override fun setValue(thisRef: T, property: KProperty<*>, value: V) {
        thisRef.setter(value)
    }
}
