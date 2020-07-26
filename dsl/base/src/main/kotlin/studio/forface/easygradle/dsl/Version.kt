@file:Suppress(
    "ReplaceGuardClauseWithFunctionCall",
    "MagicNumber", "UnderscoresInNumericLiterals",
    "unused", "MemberVisibilityCanBePrivate"
)
package studio.forface.easygradle.dsl

import studio.forface.easygradle.dsl.Version.Channel.*

/**
 * Class for libraries and applications versioning. Compatible with Android
 * @author Davide Farella
 */
class Version(
    private val major: Int,
    private val minor: Int,
    private val channel: Channel = None,
    private val patch: Int = if (channel is None) 0 else 1,
    private val build: Int = 0
) {

    constructor(major: Int, minor: Int, patch: Int) : this(major, minor, None, patch)

    override fun toString() = versionName

    /** @return the [Int] version code of the App, resolved from [major], [minor], [channel], [patch] and [build] */
    val versionCode: Int get() {
        // pattern:
        // major minor channel patch build
        // 00    00    0       00    00

        val build = build * 1
        val patch = patch * 1_00
        val channel = channel.value * 1_00_00
        val minor = minor * 1_0_00_00
        val major = major * 1_00_0_00_00

        return major + minor + channel + patch + build
    }

    /**
     * @return the [String] version name of the App, resolved from [major], [minor], [channel], [patch] and [build]
     * @see versionNameSuffix
     */
    val versionName get() = "$major.$minor$versionNameSuffix"

    /**
     * @return a [String] suffix for [versionName]. E.g. `-alpha-5`
     *
     * @throws IllegalArgumentException
     * @see preconditions
     */
    @Suppress("ConstantConditionIf")
    private val versionNameSuffix: String get() {
        preconditions()

        val (suffix, number) =
            if (build > 0) Build.suffix to buildNumber
            else channel.suffix to patch
        return "$suffix${channelNumberString(number)}"
    }

    /** @return the [Int] number for the build, needed only if [channel] is [Build] */
    private val buildNumber: Int get() {
        // pattern:
        // channel patch build
        // 0      00     00
        val build = build * 1
        val patch = patch * 1_00
        val channel = channel.value * 1_00_00

        return channel + patch + build
    }

    /** @return a [String] representing the number of the version */
    private fun channelNumberString(number: Int) =
        if (number == 0) "" else if (channel is None) ".$number" else "-$number"

    /**
     * Check the version's numbers are valid.
     * @throws IllegalArgumentException
     */
    @Suppress("ThrowsCount", "MultiLineIfElse")
    private fun preconditions() {
        if (channel is Build && build < 1) {
            throw IllegalArgumentException("'Build number' must be greater than 0 if 'channel' is 'Build'")
        }

        if (channel is Stable) {
            if (patch > 0) throw IllegalArgumentException(
                "'Stable channel' can't have a `patch number` greater " +
                    "than 0, increase the 'minor' for the next build"
            )
            if (build > 0) throw IllegalArgumentException(
                "'Stable channel' can't have a `build number` greater " +
                    "than 0, increase the 'minor' for the next build"
            )
        } else {
            if (channel !is None && build < 1 && patch < 1) {
                throw IllegalArgumentException(
                    "A `patch number` greater than 0, is required for " +
                        "'${channel.suffix.replace("-", "")}' channel"
                )
            }
        }
    }

    /** A sealed class for the Channel of the Version of the Module */
    sealed class Channel(val value: Int, val suffix: String) {
        object Build : Channel(1, "-build")
        object Alpha : Channel(2, "-alpha")
        object Beta : Channel(3, "-beta")
        object RC : Channel(4, "-rc")
        object Stable : Channel(5, "")

        internal object None : Channel(0, "")
    }
}
