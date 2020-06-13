package studio.forface.easygradle.dsl.extensions

import studio.forface.easygradle.dsl.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ExcludeTest {

    @Test
    fun `excluding remove whole module`() {

        // GIVEN
        val dep = "studio.forface.easygradle:$ANY:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assertEquals("studio.forface.easygradle", group)
        assertNull(module)
    }

    @Test
    fun `excluding remove part of module`() {

        // GIVEN
        val dep = "studio.forface.easygradle:dsl-$ANY:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assertEquals("studio.forface.easygradle", group)
        assertEquals("dsl", module)
    }

    @Test
    fun `excluding remove part of group`() {

        // GIVEN
        val dep = "studio.forface.$ANY:dsl:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assertEquals("studio.forface", group)
        assertEquals("dsl", module)
    }

    @Test
    fun `excluding remove part of group and whole module`() {

        // GIVEN
        val dep = "studio.forface.$ANY:$ANY:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assertEquals("studio.forface", group)
        assertNull(module)
    }

    @Test
    fun `excluding remove part of group part of module`() {

        // GIVEN
        val dep = "studio.forface.$ANY:dsl-$ANY:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assertEquals("studio.forface", group)
        assertEquals("dsl", module)
    }
}
