package studio.forface.easygradle.dsl

import io.mockk.mockk
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.junit.Test
import kotlin.test.assertEquals

internal class ExtensionsTest {

    @Test
    fun version() = with(mockk<DependencyHandler>(relaxed = true)) {
        `kotlin version` = "ciao"
        val result = `kotlin` version "banana"
        assertEquals("org.jetbrains.kotlin:kotlin-stdlib:banana", result)
    }
}
