@file:Suppress(
        "unused", // Public API
        "ObjectPropertyName", // val with '-'
        "RemoveRedundantBackticks" // val with backticks without special characters
)

package studio.forface.easygradle.dsl

import io.mockk.mockk
import org.gradle.api.artifacts.dsl.DependencyHandler
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LibraryReferencesTest {

    @Test
    fun testAll() = with(mockk<DependencyHandler>(relaxed = true)) {
        assertValid(
                `kotlin`,
                `kotlin-reflect`,
                `coroutines-android`,
                `serialization`
        )
    }

    private fun assertValid(vararg strings: Any) {
        strings.forEach {
            val s = it.toString()
            println(s)
            val parts = s.split(':')
            assertEquals(3, parts.size)
            parts.forEach { part -> assert(part.isNotBlank()) }
        }
    }
}