package studio.forface.easygradle.dsl.extensions

import assert4k.*
import io.mockk.mockk
import org.gradle.api.artifacts.dsl.DependencyHandler
import studio.forface.easygradle.dsl.*
import kotlin.test.Test

class ExcludeTest {

    @Test
    fun `chained excludes work properly`() {
        with(mockk<DependencyHandler>()) {
            `sqlDelight version` = "1.0"

            // GIVEN - WHEN
            val result = (
                "hello.world:lib:3.0" exclude "another.group" and forface(`any`) and sqlDelight(`any`)
            ) as ExcludingDependency
            val stringified = result.excludeNotations.map { it.toString() }

            // THEN
            assert that result.notation equals "hello.world:lib:3.0"
            assert that stringified.size equals 3
            assert that stringified contains "another.group"
            assert that stringified contains "studio.forface.$any:$any"
            assert that stringified contains "com.squareup.sqldelight:$any:1.0"
        }
    }

    @Test
    fun `excluding remove whole module`() {

        // GIVEN
        val dep = "studio.forface.easygradle:$ANY:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assert that group equals "studio.forface.easygradle"
        assert that module `is` `null`
    }

    @Test
    fun `excluding remove part of module`() {

        // GIVEN
        val dep = "studio.forface.easygradle:dsl-$ANY:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assert that group equals "studio.forface.easygradle"
        assert that module equals "dsl"
    }

    @Test
    fun `excluding remove part of group`() {

        // GIVEN
        val dep = "studio.forface.$ANY:dsl:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assert that group equals "studio.forface"
        assert that module equals "dsl"
    }

    @Test
    fun `excluding remove part of group and whole module`() {

        // GIVEN
        val dep = "studio.forface.$ANY:$ANY:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assert that group equals "studio.forface"
        assert that module `is` `null`
    }

    @Test
    fun `excluding remove part of group part of module`() {

        // GIVEN
        val dep = "studio.forface.$ANY:dsl-$ANY:1.0"

        // WHEN
        val (group, module) = RemoteDependencyParts.from(dep.excluding())

        // THEN
        assert that group equals "studio.forface"
        assert that module equals "dsl"
    }
}
