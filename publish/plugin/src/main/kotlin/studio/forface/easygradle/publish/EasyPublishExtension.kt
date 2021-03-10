package studio.forface.easygradle.publish

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.gradle.api.Project
import org.gradle.kotlin.dsl.provideDelegate
import studio.forface.easygradle.internal.ConfigReadWriteProperty
import studio.forface.easygradle.internal.assertStringsNotEmpty
import javax.inject.Inject
import kotlin.reflect.KProperty

/**
 * @return [PublishConfigBuilder]
 * @param block Lambda for setup [EasyPublishExtension]
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
@Suppress("UnnecessaryAbstractClass", "MemberVisibilityCanBePrivate")
abstract class EasyPublishExtension @Inject constructor(project: Project) {

    /**
     * Username of Maven repository.
     * Property name: `maven.user`
     */
    var username by project("", propertyName = "maven.user")

    /**
     * Password of Maven user.
     * Property name: `maven.password`
     */
    var password by project("", propertyName = "maven.password")

    /**
     * Staging profile for publications.
     * Default is [Project.getGroup]
     * */
    var stagingProfile by project(project.group.toString())

    /**
     * Base url of your Nexus instance
     */
    var baseUrl by project("", propertyName = "maven.baseUrl")

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
     * Name of the project on Maven.
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
     * Whether the publication must be published
     * Default is `false`
     * Property name: `maven.publish`
     */
    var publish by project(false, propertyName = "maven.publish")

    // region internal
    internal val devs: MutableList<Developer> by project(mutableListOf<Developer>(), propertyName = "developers")
    internal val lics: MutableList<License> by project(mutableListOf<License>(), propertyName = "licenses")

    @Suppress("UnusedPrivateMember")
    private operator fun <T : Any> Project.invoke(
        default: T,
        propertyName: String? = null,
        envName: String? = null
    ) = object : ConfigReadWriteProperty<EasyPublishExtension, T>(
        this,
        default,
        propertyName = propertyName,
        envName = envName
    ) {

        override fun String.toList(property: KProperty<*>): T? {
            @Suppress("UNCHECKED_CAST")
            return try {
                when (property) {
                    EasyPublishExtension::devs ->
                        Json.decodeFromString(ListSerializer(Developer.serializer()), this) as? T?
                    EasyPublishExtension::lics ->
                        Json.decodeFromString(ListSerializer(License.serializer()), this) as? T?
                    else -> throw AssertionError()
                }
            } catch (t: NoClassDefFoundError) { // TODO Could not initialize class kotlinx.serialization.json.Json
                logger.warn("Cannot parse ${property.name}: ${t.message}")
                null
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
            ::password,
            ::repo,
            ::artifact,
            ::scmUrl
        )
    }
    // endregion

    // region Dsl functions
    // region Licenses
    /** @return [License], use `unaryPlus` for add it to the current [EasyPublishExtension] */
    @Marker
    fun license(block: License.() -> Unit) = License().apply(block)

    /** Add receiver [License] to [EasyPublishExtension.licenses] */
    @Marker
    operator fun License.unaryPlus() {
        lics.add(this)
    }

    /** Scope for [LicensesBuilder.license] */
    @Marker
    class LicensesBuilder internal constructor()

    /** Add a set of [License]s to the current [EasyPublishExtension] */
    @Marker
    fun licenses(block: LicensesBuilder.() -> Unit) {
        LicensesBuilder().apply(block)
    }

    /** Create and add a [License] to the current [EasyPublishExtension] */
    @Suppress("unused") // Receiver as scope
    @Marker
    fun LicensesBuilder.license(block: License.() -> Unit) = +License().apply(block)
    // endregion

    // region Developers
    /** @return [Developer], use `unaryPlus` for add it to the current [EasyPublishExtension] */
    @Marker
    fun developer(block: Developer.() -> Unit) = Developer().apply(block)

    /** Add receiver [Developer] to [EasyPublishExtension.devs] */
    @Marker
    operator fun Developer.unaryPlus() {
        devs.add(this)
    }

    /** Scope for [DevelopersBuilder.developer] */
    @Marker
    class DevelopersBuilder internal constructor()

    /** Create and add a [License] to the current [EasyPublishExtension] */
    @Marker
    fun developers(block: DevelopersBuilder.() -> Unit) {
        DevelopersBuilder().apply(block)
    }

    /** Add a set of [Developer]s to the current [EasyPublishExtension] */
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
