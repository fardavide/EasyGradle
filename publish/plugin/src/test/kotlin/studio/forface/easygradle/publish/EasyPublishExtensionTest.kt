package studio.forface.easygradle.publish

import assert4k.assert
import assert4k.fails
import assert4k.that
import assert4k.with
import com.vanniktech.maven.publish.MavenPublishPluginExtension
import com.vanniktech.maven.publish.nexus.NexusOptions
import io.mockk.every
import io.mockk.mockk
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import kotlin.test.Test

class EasyPublishExtensionTest {

    private val project = mockk<Project>(relaxed = true) {
        every { group } returns "none"
        every { name } returns "none"
        every { version } returns "none"
        every { findProperty(any()) } returns null
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
        baseUrl = "base.url"
        scmUrl = "scm.url"
        signingEnabled = false
    }.build(project)

    @Test
    fun `no error thrown if signing params are not set but signingEnabled is false`() {
        // GIVEN
        val config = baseConfig.apply {
            signingEnabled = false
        }

        // WHEN - THEN
        project.publish(config)
    }

    @Test
    fun `no error thrown if correctly signing by asciiKey`() {
        // GIVEN
        val config = baseConfig.apply {
            signingEnabled = true
            signingAsciiKey = "key"
            signingPassword = "password"
        }

        // WHEN - THEN
        project.publish(config)
    }

    @Test
    fun `no error thrown if correctly signing by keyRingFile`() {
        // GIVEN
        val config = baseConfig.apply {
            signingEnabled = true
            signingKeyId = "key"
            signingPassword = "password"
            signingKeyRingFilePath = "/file/path"
        }

        // WHEN - THEN
        project.publish(config)
    }

    @Test
    fun `throw error if baseUrl is not defined`() {
        // GIVEN
        val config = baseConfig.apply {
            baseUrl = ""
        }
        val expectedMessage = "baseUrl is required, declare as 'maven.baseUrl' in your Gradle properties, " +
            "or as 'MAVEN_BASE_URL' in your environment variables"

        // WHEN - THEN
        assert that fails<IllegalArgumentException> { project.publish(config) } with expectedMessage
    }

    @Test
    fun `throw error if no signingKeyId or signingAsciiKey is defined and signingEnabled is true`() {
        // GIVEN
        val config = baseConfig.apply {
            signingEnabled = true
            signingPassword = "password"
            signingKeyRingFilePath = "/file/path"
        }
        val expectedMessage = "Signing is enabled, but no key id or ascii key is provided"

        // WHEN - THEN
        assert that fails<IllegalStateException> { project.publish(config) } with expectedMessage
    }

    @Test
    fun `throw error if signingPassword is not defined and signing is by asciiKey`() {
        // GIVEN
        val config = baseConfig.apply {
            signingEnabled = true
            signingAsciiKey = "key"
        }
        val expectedMessage = "signingPassword is required, declare as 'signing.password' in your Gradle properties, " +
            "or as 'SIGNING_PASSWORD' in your environment variables"

        // WHEN - THEN
        assert that fails<IllegalArgumentException> { project.publish(config) } with expectedMessage
    }

    @Test
    fun `throw error if signingPassword is not defined and signing is by keyRingFile`() {
        // GIVEN
        val config = baseConfig.apply {
            signingEnabled = true
            signingKeyId = "key"
            signingKeyRingFilePath = "some/path"
        }
        val expectedMessage = "signingPassword is required, declare as 'signing.password' in your Gradle properties, " +
            "or as 'SIGNING_PASSWORD' in your environment variables"

        // WHEN - THEN
        assert that fails<IllegalArgumentException> { project.publish(config) } with expectedMessage
    }

    @Test
    fun `throw error if signingKeyRingFilePath is not defined and signingEnabled by keyRingFile`() {
        // GIVEN
        val config = baseConfig.apply {
            signingEnabled = true
            signingKeyId = "key"
            signingPassword = "password"
        }
        val expectedMessage = "signingKeyRingFilePath is required, declare as 'signing.keyRingFilePath' in your " +
            "Gradle properties, or as 'SIGNING_KEY_RING_FILE_PATH' in your environment variables"

        // WHEN - THEN
        assert that fails<IllegalArgumentException> { project.publish(config) } with expectedMessage
    }
}
