package studio.forface.easygradle.publish

import assert4k.assert
import assert4k.fails
import assert4k.that
import assert4k.with
import io.mockk.every
import io.mockk.mockk
import org.gradle.api.Project
import kotlin.test.Test

class EasyPublishExtensionTest {

    private val project = mockk<Project> {
        every { group } returns "none"
        every { name } returns "none"
        every { version } returns "none"
        every { findProperty(any()) } returns null
    }
    private val baseConfig = PublishConfig {  }.build(project)

    @Test
    fun `throw error if baseUrl is not defined`() {
        val expectedMessage = "baseUrl is required, declare as 'maven.baseUrl' in your Gradle properties, " +
            "or as 'MAVEN_BASE_URL' in your environment variables"
        assert that fails<IllegalArgumentException> { baseConfig.baseUrl } with expectedMessage
    }

    @Test
    fun `throw error if signingKeyId is not defined`() {
        val expectedMessage = "signingKeyId is required, declare as 'signing.keyId' in your Gradle properties, " +
            "or as 'SIGNING_KEY_ID' in your environment variables"
        assert that fails<IllegalArgumentException> { baseConfig.signingKeyId } with expectedMessage
    }

    @Test
    fun `throw error if signingPassword is not defined`() {
        val expectedMessage = "signingPassword is required, declare as 'signing.password' in your Gradle properties, " +
            "or as 'SIGNING_PASSWORD' in your environment variables"
        assert that fails<IllegalArgumentException> { baseConfig.signingPassword } with expectedMessage
    }

    @Test
    fun `throw error if signingKeyRingFilePath is not defined`() {
        val expectedMessage = "signingKeyRingFilePath is required, declare as 'signing.keyRingFilePath' in your " +
            "Gradle properties, or as 'SIGNING_KEY_RING_FILE_PATH' in your environment variables"
        assert that fails<IllegalArgumentException> { baseConfig.signingKeyRingFilePath } with expectedMessage
    }
}
