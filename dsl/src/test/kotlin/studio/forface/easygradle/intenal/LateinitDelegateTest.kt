package studio.forface.easygradle.intenal

import studio.forface.easygradle.internal.lateinit
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.asserter

/**
 * Test suite for [lateinit]
 * Verifies that:
 * * property works correctly if initialized
 * * exception is thrown with proper message if not initialized
 * * exception message show correct property name
 *
 * @author Davide Farella
 */
internal class LateinitDelegateTest {

    @Test
    fun `lateinit works correctly if version is set`() {
        initializedVersion = "1.2.3"
        assertEquals("1.2.3", initializedVersion)
    }

    @Test
    fun `lateinit show proper exception if version is not set`() {
        assertFailsWithPropertyName("notInitializedVersion") {
            notInitializedVersion
        }
    }

    @Test
    fun `lateinit exception apply backticks when required`() {
        assertFailsWithPropertyName("_a") {
            _a
        }
        assertFailsWithPropertyName("_9a") {
            _9a
        }
        assertFailsWithPropertyName("`dash-version`") {
            `dash-version`
        }
        assertFailsWithPropertyName("`space version`") {
            `space version`
        }
    }

    private fun assertFailsWithPropertyName(propertyName: String, block: () -> Unit): Throwable {
        runCatching(block).fold(
            onSuccess = { asserter.fail("Expected an exception to be thrown, but was completed successfully.") },
            onFailure = {
                val (match) = PROPERTY_NAME_MESSAGE_REGEX.find(it.message ?: "")!!.destructured
                return if (match == propertyName) it
                else asserter.fail("Property name '$propertyName' does not match the error message name: $match")
            }
        )
    }

    private companion object {
        val PROPERTY_NAME_MESSAGE_REGEX = """'(.*)'.*^ *(.+) =.*""".toRegex(
            setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.MULTILINE)
        )
    }
}

private var initializedVersion by lateinit()
private var notInitializedVersion by lateinit()
private var _a by lateinit()
private var _9a by lateinit()
private var `dash-version` by lateinit()
private var `space version` by lateinit()
