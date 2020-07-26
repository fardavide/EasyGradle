@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl

import org.gradle.api.Project

/** @return `true` if [Project] has any Android plugin */
val Project.isAndroid get() =
    plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")
