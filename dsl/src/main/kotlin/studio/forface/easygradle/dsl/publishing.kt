@file:Suppress("unused")
package studio.forface.easygradle.dsl

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.provideDelegate
import studio.forface.easygradle.internal.ConfigReadWriteProperty
import studio.forface.easygradle.internal.assertStringsNotEmpty
import studio.forface.easygradle.internal.useIfNotBlank
import kotlin.reflect.KProperty

@Deprecated("Use 'publish'", ReplaceWith("publish(baseBlock, artifact, block)"))
fun Project.publishMultiplatform(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    publish(baseBlock, artifact, block)
}

@Deprecated("Use 'publish'", ReplaceWith("publish(baseBlock, artifact, block)"))
fun Project.publishJvm(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    publish(baseBlock, artifact, block)
}

@Deprecated("Use 'publish'", ReplaceWith("publish(baseBlock, artifact, block)"))
fun Project.publish2(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    publish(baseBlock, artifact, block)
}

/**
 * Apply publish script to the receiver [Project]
 *
 * Params for [PublishConfig] can be set:
 * * programmatically
 * * in `gradle.properties`
 * * in Environment variable
 * @see PublishConfig params for names and format
 * Objects and lists will respect the JSON standard format
 *
 *
 * @param artifact Optional [PublishConfig.artifact] for the [PublishConfig], this is useful when we have a stored
 *   common [PublishConfig] for the project and we want to apply it for a single module
 *
 * @param baseBlock Optional Lambda previously created by [publishConfig] for have a base setup for [PublishConfig]
 *
 * @param block Lambda for setup [PublishConfig]
 */
fun Project.publish(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    val c = PublishConfig(this).apply {
        baseBlock?.let { this.baseBlock(project) }
        artifact?.let { this.artifact = it }
        this.block(project)
        validate()
    }

    extra["GROUP"] = c.group
    extra["POM_ARTIFACT_ID"] = c.artifact
    extra["VERSION_NAME"] = c.versionName

    extra["POM_NAME"] = c.name
    extra["POM_DESCRIPTION"] = c.description
//    extra["POM_INCEPTION_YEAR"] =

//    extra["POM_url"] =
    extra["POM_SCM_URL"] = c.scmUrl
    extra["POM_SCM_CONNECTION"] = c.scmConnection.useIfNotBlank { "scm:git:$it" }
    extra["POM_SCM_DEV_CONNECTION"] = c.scmDevConnection.useIfNotBlank { "scm:git:$it" }

    extra["RELEASE_SIGNING_ENABLED"] = false // TODO get from c

    c.lics.firstOrNull()?.let { lic ->
        extra["POM_LICENCE_NAME"] = lic.name
        extra["POM_LICENCE_URL"] = lic.url
        extra["POM_LICENCE_DIST"] = "repo"
    }

    c.devs.firstOrNull()?.let { dev ->
        extra["POM_DEVELOPER_ID"] = dev.id
        extra["POM_DEVELOPER_NAME"] = dev.name
        extra["POM_DEVELOPER_URL"] = dev.email
    }

    apply(plugin = "com.vanniktech.maven.publish")

    mavenPublish {
        targets.getByName("uploadArchives") {
            val target = c.organization.takeIf { it.isNotBlank() } ?: c.username
            val override = if (c.override) 1 else 0
            val publish = if (c.publish) 1 else 0

            releaseRepositoryUrl = "https://api.bintray.com/maven/" +
                "$target/" +
                "${c.repo}/" +
                "${c.name}/" +
                ";publish=$publish" +
                ";override=$override"
            repositoryUsername = c.username
            repositoryPassword = c.apiKey
        }
    }
}

/**
 * @return [PublishConfigBuilder]
 * @param block Lambda for setup [PublishConfig]
 */
fun publishConfig(block: PublishConfigBuilder): PublishConfigBuilder = { apply { this.block(it) } }

/**
 * @return [PublishConfig]
 * @param block Lambda for setup [PublishConfig]
 */
fun Project.buildPublishConfig(block: PublishConfigBuilder): PublishConfig =
    PublishConfig(this).apply { block(this@buildPublishConfig) }

/**
 * Holds params for publication.
 * Each parameter can be set
 * * programmatically
 * * in `gradle.properties`
 * * as Environment variable
 * properties names in gradle.properties reflect the field name of the variable and environment variables reflect the
 * property name name as uppercase snake_case where not specified differently.
 * See gradle.properties.template for more examples
 */
class PublishConfig internal constructor(project: Project) {

    /**
     * Username of Bintray user.
     * Property name: `bintray.user`
     */
    var username by project("", propertyName = "bintray.user")

    /**
     * Api Key of Bintray user.
     * Property name: `bintray.apikey`
     */
    var apiKey by project("", propertyName = "bintray.apikey")

    /** Optional name of the organization */
    var organization by project("")

    /**
     * Name of the project on Bintray.
     * Default is capitalized [Project.getName]
     * Property name: `projectName`
     */
    var name by project(project.name.capitalize(), "projectName")

    /** Name of the Repository where to publish */
    var repo by project("")

    /**
     * Name of the artifact to public.
     * Default is [Project.getName]
     */
    var artifact by project(project.name)

    /** Group of the Library */
    var group by project("")

    /**
     * Version of the library
     * Property name: `version`
     */
    var versionName by project("", "version")

    /** Optional description of the library */
    var description by project("")

    /** Optional website url of the library */
    var siteUrl by project("")

    /**
     * Scm url of the library
     * Property name: `scm.url`
     */
    var scmUrl by project("", "scm.url")

    /**
     * Scm connection of the library
     * Property name: `scm.connection`
     */
    var scmConnection by project("", "scm.connection")

    /**
     * Scm Dev connection of the library
     * Property name: `scm.devConnection`
     */
    var scmDevConnection by project("", "scm.devConnection")

    /**
     * Whether the publication must override a pre-existent one
     * Default is `false`
     * Property name: `bintray.override`
     */
    var override by project(false, propertyName = "bintray.override")

    /**
     * Whether the publication must be published
     * Default is `false`
     * Property name: `bintray.publish`
     */
    var publish by project(false, propertyName = "bintray.publish")

    // region internal
    internal val devs: MutableList<Developer> by project(mutableListOf<Developer>(), propertyName = "developers")
    internal val lics: MutableList<License> by project(mutableListOf<License>(), propertyName = "licenses")

    @OptIn(ImplicitReflectionSerializer::class, UnstableDefault::class)
    private operator fun <T : Any> Project.invoke(
        default: T,
        propertyName: String? = null,
        envName: String? = null
    ) = object : ConfigReadWriteProperty<PublishConfig, T>(
        this,
        default,
        propertyName = propertyName,
        envName = envName
    ) {

        override fun String.toList(property: KProperty<*>): T? {
            @Suppress("UNCHECKED_CAST")
            return when (property) {
                PublishConfig::devs -> Json.parseList<Developer>(this) as? T?
                PublishConfig::lics -> Json.parseList<License>(this) as? T?
                else -> throw AssertionError()
            }
        }
    }

    internal fun validate() {
        for (license in lics) license.validate()
        for (developer in devs) developer.validate()
        assertStringsNotEmpty(
            ::group,
            ::versionName,
            ::username,
            ::apiKey,
            ::repo,
            ::artifact,
            ::scmUrl,
            ::scmConnection,
            ::scmDevConnection
        )
    }
    // endregion

    // region Dsl functions
    // region Licenses
    /** @return [License], use `unaryPlus` for add it to the current [PublishConfig] */
    @Marker
    fun license(block: License.() -> Unit) = License().apply(block)

    /** Add receiver [License] to [PublishConfig.licenses] */
    @Marker
    operator fun License.unaryPlus() {
        lics.add(this)
    }

    /** Scope for [LicensesBuilder.license] */
    @Marker
    class LicensesBuilder internal constructor()

    /** Add a set of [License]s to the current [PublishConfig] */
    @Marker
    fun licenses(block: LicensesBuilder.() -> Unit) {
        LicensesBuilder().apply(block)
    }

    /** Create and add a [License] to the current [PublishConfig] */
    @Suppress("unused") // Receiver as scope
    @Marker
    fun LicensesBuilder.license(block: License.() -> Unit) = +License().apply(block)
    // endregion

    // region Developers
    /** @return [Developer], use `unaryPlus` for add it to the current [PublishConfig] */
    @Marker
    fun developer(block: Developer.() -> Unit) = Developer().apply(block)

    /** Add receiver [Developer] to [PublishConfig.devs] */
    @Marker
    operator fun Developer.unaryPlus() {
        devs.add(this)
    }

    /** Scope for [DevelopersBuilder.developer] */
    @Marker
    class DevelopersBuilder internal constructor()

    /** Create and add a [License] to the current [PublishConfig] */
    @Marker
    fun developers(block: DevelopersBuilder.() -> Unit) {
        DevelopersBuilder().apply(block)
    }

    /** Add a set of [Developer]s to the current [PublishConfig] */
    @Suppress("unused") // Receiver as scope
    @Marker
    fun DevelopersBuilder.developer(block: Developer.() -> Unit) = +Developer().apply(block)
    // endregion
    // endregion

    // region Children
    @Marker
    @Serializable
    class Developer internal constructor() {
        var name: String = ""
        var id: String = name
        var email: String = ""

        internal fun validate() {
            assertStringsNotEmpty(::id, ::name, ::email)
        }

        override fun toString() = "id: $id, name: $name, email: $email"
    }

    @Marker
    @Serializable
    class License internal constructor() {
        var name: String = ""
        var url: String = ""

        internal fun validate() {
            assertStringsNotEmpty(::name, ::url)
        }

        override fun toString() = "$name: $url"
    }
    // endregion

    @DslMarker
    annotation class Marker
}

private var PublishConfig.version: Version
    get() = throw UnsupportedOperationException()
    set(value) { versionName = value.versionName }

/** Lambda for build a [PublishConfig] within a [Project] */
typealias PublishConfigBuilder = PublishConfig.(Project) -> Unit
