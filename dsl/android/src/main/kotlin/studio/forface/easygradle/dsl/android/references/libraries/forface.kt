@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.lateinit


var `fluentNotifications version` by lateinit()
var `theia version` by lateinit()
var `viewStateStore version` by lateinit()


fun fluentNotifications() = forface("fluentnotifications") version `fluentNotifications version`

fun theia() = forface("theia") version `theia version`

fun viewStateStore(module: String? = null) =
    forface("viewstatestore", moduleSuffix = module) version `viewStateStore version`
