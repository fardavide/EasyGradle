@file:Suppress("RemoveRedundantBackticks")

private object Version {
    const val androidGradlePlugin =     "3.5.0-rc03"    // Updated: Aug 08, 2019
    const val bintrayGradlePlugin =     "1.8.4"         // Updated: Jul 08, 2018
    const val dokkaGradlePlugin =       "0.9.18"        // Updated: Mar 19, 2019
    const val junit =                   "4.13-beta-3"   // Updated: May 05, 2019
    const val kotlin =                  "1.3.50"        // Updated: Aug 22, 2019
    const val mockk =                   "1.9.3"         // Updated: Mar 25, 2019
}

object Lib {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Version.androidGradlePlugin}"
    const val bintrayGradlePlugin = "com.jfrog.bintray.gradle:gradle-bintray-plugin:${Version.bintrayGradlePlugin}"
    const val dokkaGradlePlugin = "org.jetbrains.dokka:dokka-gradle-plugin:${Version.dokkaGradlePlugin}"
    const val dokkaAndroidGradlePlugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:${Version.dokkaGradlePlugin}"

    const val jUnit = "junit:junit:${Version.junit}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Version.kotlin}"
    const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Version.kotlin}"
    const val mockk = "io.mockk:mockk:${Version.mockk}"
}
