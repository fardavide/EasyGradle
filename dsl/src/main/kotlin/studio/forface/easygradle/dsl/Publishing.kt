@file:Suppress("MemberVisibilityCanBePrivate", "unused")
package studio.forface.easygradle.dsl

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.provideDelegate
import studio.forface.easygradle.internal.ConfigReadWriteProperty
import studio.forface.easygradle.internal.PublicationsBundle
import studio.forface.easygradle.internal.PublicationsBundleBuilder
import studio.forface.easygradle.internal.PublishType.JVM_ONLY
import studio.forface.easygradle.internal.PublishType.MULTI_PLATFORM
import studio.forface.easygradle.internal._publish
import studio.forface.easygradle.internal.assertStringsNotEmpty
import kotlin.reflect.KProperty

/**
 * Apply publish script to the given [MULTI_PLATFORM] module
 * NOTE: remember to setup library variants for Android sourceSets. Example:
 * ```
android {
    publishLibraryVariants("release", "debug")
}
 * ```
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
fun Project.publishMultiplatform(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    _publish(baseBlock, artifact, block, MULTI_PLATFORM) {
        PublicationsBundle(sourceSets["main"].allSource)
    }
}

/**
 * Apply publish script to the given [JVM_ONLY] module
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
fun Project.publishJvm(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    _publish(baseBlock, artifact, block, JVM_ONLY) {
        PublicationsBundle(sourceSets["main"].allSource)
    }
}

/**
 * @return [PublishConfigBuilder]
 * @param block Lambda for setup [PublishConfig]
 */
fun publishConfig(block: PublishConfigBuilder): PublishConfigBuilder = { apply { this.block(it) } }

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

    /** Version of the library */
    var version by project("")

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
     * Property name: `scm.devconnection`
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
    internal lateinit var publicationsBundleBuilder: PublicationsBundleBuilder

    @OptIn(ImplicitReflectionSerializer::class)
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
        assertStringsNotEmpty(::username, ::apiKey, ::repo, ::artifact, ::scmUrl, ::scmConnection, ::scmDevConnection)
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

/** Lambda for build a [PublishConfig] within a [Project] */
typealias PublishConfigBuilder = PublishConfig.(Project) -> Unit
