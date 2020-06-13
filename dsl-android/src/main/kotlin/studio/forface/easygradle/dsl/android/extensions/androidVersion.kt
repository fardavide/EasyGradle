@file:Suppress("unused", "PackageDirectoryMismatch")

package studio.forface.easygradle.dsl.android

import com.android.build.gradle.internal.dsl.DefaultConfig
import studio.forface.easygradle.dsl.*
import studio.forface.easygradle.internal.writeOnly

/**
 * Set [Version.versionName] and [Version.versionCode] into Android [DefaultConfig]
 * E.g.
 * >
android {
    defaultConfig {
        version = Version(1, 0, Stable, 0, 0)
    }
}
 */
var DefaultConfig.version: Version by writeOnly {
    versionCode = it.versionCode
    versionName = it.versionName
}
