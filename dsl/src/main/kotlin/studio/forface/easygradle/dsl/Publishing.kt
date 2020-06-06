@file:Suppress(
        "MemberVisibilityCanBePrivate", "unused" // Public APIs
)
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
import studio.forface.easygradle.internal._publish
import studio.forface.easygradle.internal.assertStringsNotEmpty
import kotlin.reflect.KProperty

/**
 * Apply publish script to the given module
 *
 * Params for [PublishConfig] can be set in `gradle.properties`
 * @see PublishConfig params for names and format
 * Objects and lists will respect the JSON standard format
 *
 *
 * @param artifact Optional [PublishConfig.artifact] for the [PublishConfig], this is useful when we have a stored
 *   common [PublishConfig] for the project and we want to apply it for a single module
 *
 * @param baseBlock Optional Lambda previously created by [publishConfig] for have a base setup for [PublishConfig]
 *
 * @param lazy Whether the configuration should be applied lazily. If `true` if will be setup in [Project.afterEvaluate]
 *   Default is `true`. You might need to set to `false` if this method is already called inside a
 *   [Project.afterEvaluate] block
 *
 * @param block Lambda for setup [PublishConfig]
 */
fun Project.publish(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    lazy: Boolean = true,
    block: PublishConfigBuilder = {}
) {
    _publish(baseBlock, artifact, lazy, block) {
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
 * Each parameter can be se programmatically of in `gradle.properties`; properties names in gradle.properties reflect
 * the field name of the variable, where not specified differently.
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

    /** Desired Group for publication, excluding group id */
    var bintrayGroup by project("")

    /** Id for the group [bintrayGroup] */
    var groupId by project("")

    /**
     * Name of the artifact to public.
     * Default is [Project.getName]
     */
    var artifact by project(project.name)

    // TODO: remove in 1.5
    @Deprecated("Use 'repo' instead", ReplaceWith("repo"))
    var groupName by project("")

    /** Name of the Repository where to publish */
    var repo by project("")

    /** Version of the library */
    var version by project("")

    /** Optional description of the library */
    var description by project("")

    /** Optional website url of the library */
    var siteUrl by project("")

    /** Optional Git url of the library */
    var gitUrl by project("")

    /**
     * Name of the module containing the sources to publish
     * Default is [artifact]
     */
    var projectName: String? = artifact

    /**
     * Whether the publication must override a pre-existent one
     * Default is `false`
     * Property name: `publish.override`
     */
    var override by project(false, propertyName = "publish.override")

    /**
     * Whether the download number must be visible publicly.
     * Default is `true`
     */
    var publicDownloadNumber by project(true)

    // region internal
    internal val devs: MutableList<Developer> by project(mutableListOf<Developer>(), propertyName = "developers")
    internal val lics: MutableList<License> by project(mutableListOf<License>(), propertyName = "licenses")
    internal lateinit var publicationsBundleBuilder: PublicationsBundleBuilder

    @OptIn(ImplicitReflectionSerializer::class)
    private operator fun <T : Any> Project.invoke(
        default: T,
        propertyName: String? = null
    ) = object : ConfigReadWriteProperty<PublishConfig, T>(this, default, propertyName = propertyName) {

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
        assertStringsNotEmpty(::username, ::apiKey, ::bintrayGroup, ::groupId, ::artifact, ::repo)
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
