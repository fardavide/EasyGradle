package studio.forface.easygradle.publish

import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import org.gradle.api.Project
import org.gradle.kotlin.dsl.provideDelegate
import studio.forface.easygradle.internal.ConfigReadWriteProperty
import studio.forface.easygradle.internal.assertStringsNotEmpty
import kotlin.reflect.KProperty

/**
 * @return [PublishConfigBuilder]
 * @param block Lambda for setup [PublishConfig]
 */
fun PublishConfig(block: PublishConfigBuilder) = block

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
@Suppress("MemberVisibilityCanBePrivate")
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

    /** Name of the Repository where to publish */
    var repo by project("")

    /** Group of the Library */
    var group by project(project.group.toString())

    /**
     * Name of the artifact to public.
     * Default is [Project.getName]
     */
    var artifact by project(project.name)

    /**
     * Name of the project on Bintray.
     * Default is `$group:$artifact`
     * Property name: `projectName`
     */
    var name by project("$group:$artifact", "projectName")

    /**
     * Version of the library
     * Property name: `version`
     */
    var versionName by project(project.version.toString(), "version")

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

    internal fun buildBintrayUrl(): String {
        val target = organization.takeIf { it.isNotBlank() } ?: username
        val override = if (override) 1 else 0
        val publish = if (publish) 1 else 0

        return "https://api.bintray.com/maven/" +
            "$target/" +
            "$repo/" +
            "$name/" +
            ";publish=$publish" +
            ";override=$override"
    }

    @OptIn(UnstableDefault::class)
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
                PublishConfig::devs -> Json.parse<Developer>(this) as? T?
                PublishConfig::lics -> Json.parse<License>(this) as? T?
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
            ::scmUrl
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