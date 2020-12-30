@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import studio.forface.easygradle.internal.lateinit


var `detekt version` by lateinit()
var `detekt-code-analysis version` by lateinit()
var `koin version` by lateinit()
var `mockK version` by lateinit()


fun detekt(moduleSuffix: String) =
    dependency("io.gitlab.arturbosch", groupName = "detekt", moduleSuffix = moduleSuffix) version `detekt version`

fun detektCodeAnalysis() =
    dependency("pm.algirdas", "detekt", "codeanalysis") version `detekt-code-analysis version`

fun koin(module: String? = null) =
    dependency("org.koin", module = "koin", moduleSuffix = module) version `koin version`

fun mockK(moduleSuffix: String? = null) =
    dependency("io.mockk", module = "mockk", moduleSuffix = moduleSuffix) version `mockK version`
