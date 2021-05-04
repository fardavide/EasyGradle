package studio.forface.easygradle.publish

import assert4k.*
import com.vanniktech.maven.publish.MavenPublishPluginExtension
import com.vanniktech.maven.publish.SonatypeHost
import com.vanniktech.maven.publish.nexus.NexusOptions
import io.mockk.every
import io.mockk.mockk
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.extra
import kotlin.test.Test

class EasyPublishExtensionTest {

    private val project = mockk<Project>(relaxed = true) {

        val extrasMap = mutableMapOf<String, Any?>()

        every { group } returns "none"
        every { name } returns "none"
        every { version } returns "none"
        every { extra } answers {
            mockk extra@ {
                every { this@extra.get(any()) } answers {
                    extrasMap[firstArg()]
                }
                every { this@extra.set(any(), any()) } answers {
                    extrasMap[firstArg()] = secondArg()
                }
            }
        }
        every { findProperty(any()) } answers {
            extrasMap[firstArg()]
        }
        every { setProperty(any(), any()) } answers {
            extrasMap[firstArg()] = secondArg()
        }
        every {
            (this@mockk as ExtensionAware).extensions
                .configure<MavenPublishPluginExtension.() -> Unit>("mavenPublish", any())
        } answers {

            val mockNexusOptions = mockk<NexusOptions>(relaxed = true)
            val publishPluginExtension = mockk<MavenPublishPluginExtension> {
                every { nexus(any()) } answers {
                    val action = firstArg<Action<NexusOptions>>()
                    action.execute(mockNexusOptions)
                }
            }

            val action = secondArg<Action<MavenPublishPluginExtension>>()
            action.execute(publishPluginExtension)
        }
    }
    private val baseConfig = PublishConfig {
        username = "username"
        password = "password"
        scmUrl = "scm.url"
        signingEnabled = false
    }
    private val baseExtension = baseConfig.build(project)

    @Test
    fun `no error thrown if signing params are not set but signingEnabled is false`() {
        // GIVEN
        val ext = baseExtension.apply {
            signingEnabled = false
        }

        // WHEN - THEN
        project.publish(ext)
    }

    @Test
    fun `no error thrown if correctly signing by asciiKey`() {
        // GIVEN
        val ext = baseExtension.apply {
            signingEnabled = true
            signingAsciiKey = "key"
            signingPassword = "password"
        }

        // WHEN - THEN
        project.publish(ext)
    }

    @Test
    fun `no error thrown if correctly signing by keyRingFile`() {
        // GIVEN
        val ext = baseExtension.apply {
            signingEnabled = true
            signingKeyId = "key"
            signingPassword = "password"
            signingKeyRingFilePath = "/file/path"
        }

        // WHEN - THEN
        project.publish(ext)
    }

    @Test
    fun `throw error if no signingKeyId or signingAsciiKey is defined and signingEnabled is true`() {
        // GIVEN
        val ext = baseExtension.apply {
            signingEnabled = true
            signingPassword = "password"
            signingKeyRingFilePath = "/file/path"
        }
        val expectedMessage = "Signing is enabled, but no key id or ascii key is provided"

        // WHEN - THEN
        assert that fails<IllegalStateException> { project.publish(ext) } with expectedMessage
    }

    @Test
    fun `throw error if signingPassword is not defined and signing is by asciiKey`() {
        // GIVEN
        val ext = baseExtension.apply {
            signingEnabled = true
            signingAsciiKey = "key"
        }
        val expectedMessage = "signingPassword is required, declare as 'signing.password' in your Gradle properties, " +
            "or as 'SIGNING_PASSWORD' in your environment variables"

        // WHEN - THEN
        assert that fails<IllegalArgumentException> { project.publish(ext) } with expectedMessage
    }

    @Test
    fun `throw error if signingPassword is not defined and signing is by keyRingFile`() {
        // GIVEN
        val ext = baseExtension.apply {
            signingEnabled = true
            signingKeyId = "key"
            signingKeyRingFilePath = "some/path"
        }
        val expectedMessage = "signingPassword is required, declare as 'signing.password' in your Gradle properties, " +
            "or as 'SIGNING_PASSWORD' in your environment variables"

        // WHEN - THEN
        assert that fails<IllegalArgumentException> { project.publish(ext) } with expectedMessage
    }

    @Test
    fun `throw error if signingKeyRingFilePath is not defined and signingEnabled by keyRingFile`() {
        // GIVEN
        val ext = baseExtension.apply {
            signingEnabled = true
            signingKeyId = "key"
            signingPassword = "password"
        }
        val expectedMessage = "signingKeyRingFilePath is required, declare as 'signing.keyRingFilePath' in your " +
            "Gradle properties, or as 'SIGNING_KEY_RING_FILE_PATH' in your environment variables"

        // WHEN - THEN
        assert that fails<IllegalArgumentException> { project.publish(ext) } with expectedMessage
    }

    @Test
    fun `sonatypeHost Default is used if no specified`() {
        // GIVEN
        val ext = baseConfig.build(project)

        // WHEN - THEN
        assert that ext.sonatypeHost equals SonatypeHost.DEFAULT
    }

    @Test
    fun `sonatypeHost is set correctly from code`() {
        // GIVEN
        val ext = baseExtension.apply {
            sonatypeHost = SonatypeHost.S01
        }

        // WHEN - THEN
        assert that ext.sonatypeHost equals SonatypeHost.S01
    }

    @Test
    fun `sonatypeHost is set correctly from Gradle`() {
        // GIVEN
        project.extra["sonatypeHost"]
        project.extra["sonatypeHost"] = "S01"
        val ext = baseConfig.build(project)

        // WHEN - THEN
        assert that ext.sonatypeHost equals SonatypeHost.S01
    }
}
