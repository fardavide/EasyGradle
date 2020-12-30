@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.lateinit


var `retrofit-kotlin-serialization version` by lateinit()
var `timber version` by lateinit()


fun retrofitKotlinSerialization() =
    jakeWharton(
        "retrofit",
        "retrofit2-kotlinx-serialization-converter"
    ) version `retrofit-kotlin-serialization version`

fun timber() =
    jakeWharton("timber") version `timber version`
