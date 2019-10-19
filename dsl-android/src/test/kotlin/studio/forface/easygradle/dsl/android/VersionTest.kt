package studio.forface.easygradle.dsl.android

import org.junit.Test
import studio.forface.easygradle.dsl.android.Version.Channel.Beta
import studio.forface.easygradle.dsl.android.Version.Channel.Stable
import kotlin.test.assertEquals

internal class VersionTest {

    @Test
    fun `versionName works properly for long version`() {
        assertEquals("1.0", Version(1, 0, Stable, 0, 0).versionName)
        assertEquals("1.0-beta-3", Version(1, 0, Beta, 3, 0).versionName)
        assertEquals("1.0-build-30301", Version(1, 0, Beta, 3, 1).versionName)
    }

    @Test
    fun `versionName works properly for short version`() {
        assertEquals("1.0", Version(1, 0).versionName)
        assertEquals("1.0", Version(1, 0, 0).versionName)
        assertEquals("1.0.1", Version(1, 0, 1).versionName)
        assertEquals("1.0.3", Version(1, 0, 3).versionName)
    }
}