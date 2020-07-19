package studio.forface.easygradle.publish

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.gradle.api.Project
import studio.forface.easygradle.dsl.*
import kotlin.test.Test
import kotlin.test.assertEquals

class EasyPublishExtensionTest {

    @Test
    fun `verify generated Bintray url by DSL`() {

        // GIVEN
        val project = mockk<Project>() {
            every { group } returns "none"
            every { name } returns "none"
            every { version } returns "none"
            every { findProperty(any()) } returns null
        }
        val configBuilder = PublishConfig {
            version = Version(0, 1)
            group = "my.group"
            name = "my-package"
            repo = "MyRepository"
            publish = false
            override = true

            scmUrl = "gitUrl"

            username = "User"
            apiKey = "MY_API_KEY"
        }

        // WHEN
        val config = configBuilder.build(project)
            .also { it.validate() }
        val bintrayUrl = config.buildBintrayUrl()

        // THEN
        assertEquals(
            "https://api.bintray.com/maven/User/MyRepository/my-package/;publish=0;override=1",
            bintrayUrl
        )
    }

    @Test
    fun `verify generated Bintray url by properties`() {
        mockkStatic(System::class)
        every { System.getenv(any()) } returns null

        // GIVEN
        val props = mapOf(
            "repo" to "MyRepository",
            "bintray.publish" to "false",
            "bintray.override" to "true",
            "scm.url" to "gitUrl",
            "bintray.user" to "User",
            "bintray.apikey" to "MY_API_KEY"
        )
        val project = mockk<Project>() {
            every { version } returns Version(0, 1)
            every { group } returns "my.group"
            every { name } returns "my-package"
            every { findProperty(any()) } answers { props[firstArg()] }
        }
        val configBuilder = PublishConfig {}

        // WHEN
        val config = configBuilder.build(project)
            .also { it.validate() }
        val bintrayUrl = config.buildBintrayUrl()

        // THEN
        assertEquals(
            "https://api.bintray.com/maven/User/MyRepository/my.group:my-package/;publish=0;override=1",
            bintrayUrl
        )

        unmockkStatic(System::class)
    }

    @Test
    fun `verify generated Bintray url by environment variable`() {
        mockkStatic(System::class)
        every { System.getenv("BINTRAY_USER") } returns "User"
        every { System.getenv("BINTRAY_APIKEY") } returns "MY_API_KEY"

        // GIVEN
        val props = mapOf(
            "repo" to "MyRepository",
            "bintray.publish" to "false",
            "bintray.override" to "true",
            "scm.url" to "gitUrl",
            "bintray.user" to "none"
        )
        val project = mockk<Project>() {
            every { version } returns Version(0, 1)
            every { group } returns "my.group"
            every { name } returns "my-package"
            every { findProperty(any()) } answers { props[firstArg()] }
        }
        val configBuilder = PublishConfig {}

        // WHEN
        val config = configBuilder.build(project)
            .also { it.validate() }
        val bintrayUrl = config.buildBintrayUrl()

        // THEN
        assertEquals(
            "https://api.bintray.com/maven/User/MyRepository/my.group:my-package/;publish=0;override=1",
            bintrayUrl
        )
        assertEquals("MY_API_KEY", config.apiKey)

        unmockkStatic(System::class)
    }
}
