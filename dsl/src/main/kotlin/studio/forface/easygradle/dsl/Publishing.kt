@file:Suppress(
        "MemberVisibilityCanBePrivate", "unused" // Public APIs
)
package studio.forface.easygradle.dsl

import com.jfrog.bintray.gradle.BintrayExtension
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.parseList
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*
import studio.forface.easygradle.dsl.internal.ConfigReadWriteProperty
import studio.forface.easygradle.dsl.internal.assertStringsNotEmpty
import java.util.*
import kotlin.reflect.KProperty

/*
 * A file containing params for publishing on Bintray
 * Author: Davide Farella
 */

/**
 * Apply studio.forface.easygradle.dsl.publish script to the given module
 *
 * Params for [PublishConfig] can be set in `gradle.properties`
 * @see PublishConfig params for names and format
 * Objects and lists will respect the JSON standard format
 *
 *
 * @param config Optional [PublishConfig] to start from
 *
 * @param artifact Optional [PublishConfig.artifact] for the [PublishConfig], this is useful when we have a stored
 * common [PublishConfig] for the project and we want to apply it for a single module
 *
 * @param baseBlock Optional Lambda previously created by [publishConfig] for have a base setup for [PublishConfig]
 *
 * @param block Lambda for setup [PublishConfig]
 */
fun Project.publish(
        config: PublishConfig = PublishConfig(this),
        baseBlock: PublishConfigBuilder? = null,
        artifact: String = config.artifact,
        block: PublishConfigBuilder = {}
) {
    val project = this
    val validConfig = config.apply {
        baseBlock?.let { this.baseBlock(project) }
        this.artifact = artifact
        this.block(project)
        validate()
    }
    publish(validConfig)
}

/**
 * @return [PublishConfigBuilder]
 * @param block Lambda for setup [PublishConfig]
 */
fun publishConfig(block: PublishConfigBuilder): PublishConfigBuilder = { apply { this.block(it) } }

class PublishConfig internal constructor(project: Project) {
    // region Params
    var username                                by project("", propertyName = "bintray.user")
    var apiKey                                  by project("", propertyName = "bintray.apikey")
    var bintrayGroup                            by project("")
    var groupId                                 by project("")
    var artifact                                by project("")
    var groupName                               by project(artifact)
    var version                                 by project("")
    var description                             by project("")
    var siteUrl                                 by project("")
    var gitUrl                                  by project("")
    internal val devs: MutableList<Developer>   by project(mutableListOf<Developer>(), propertyName = "developers") // TODO include in `studio.forface.easygradle.dsl.publish`
    internal val lics: MutableList<License>     by project(mutableListOf<License>(), propertyName = "licenses")
    var projectName: String? =                  artifact
    var override                                by project(false, propertyName = "publish.override")
    var publicDownloadNumber                    by project(true)
    // endregion

    @UseExperimental(ImplicitReflectionSerializer::class)
    private operator fun <T: Any> Project.invoke(
            default: T,
            propertyName: String? = null
    ) = object : ConfigReadWriteProperty<PublishConfig, T>(this, default, propertyName = propertyName) {

        override fun String.toList(property: KProperty<*>): T? {
            @Suppress("UNCHECKED_CAST")
            return when(property) {
                PublishConfig::devs -> Json.parseList<Developer>(this) as? T?
                PublishConfig::lics -> Json.parseList<License>(this) as? T?
                else -> throw AssertionError()
            }
        }
    }

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

    internal fun validate() {
        for(license in lics) license.validate()
        for(developer in devs) developer.validate()
        assertStringsNotEmpty(::username, ::apiKey, ::bintrayGroup, ::groupId, ::artifact, ::groupName)
    }
    // endregion

    // region Children
    @Marker
    @Serializable
    class Developer internal constructor() {
        var id: String =        ""
        var name: String =      id
        var email: String =     ""

        internal fun validate() {
            assertStringsNotEmpty(::id, ::name, ::email)
        }

        override fun toString() = "id: $id, name: $name, email: $email"
    }
    @Marker
    @Serializable
    class License internal constructor() {
        var name: String =      ""
        var url: String =       ""

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

private fun PublishConfig.projectFor(project: Project) =
        if (projectName?.isBlank() == false) project.project(":$projectName") else project

@Suppress("UnstableApiUsage")
private fun Project.publish(c: PublishConfig) = with(c.projectFor(this)) {
    apply(plugin = "com.jfrog.bintray")
    apply(plugin = "maven-publish")

    group = "${c.bintrayGroup}.${c.groupId}"
    version = c.version

    val sourcesJar = tasks.create("sourcesJar", Jar::class) {
        archiveClassifier.set("sources")
        if ("main" in sourceSets.names)
            from(sourceSets["main"].allSource)
        // Android
        else if ("release" in components.names)
            from(components["release"])
    }

    publishing {
        publications.create<MavenPublication>(c.artifact) {
            groupId = "${c.bintrayGroup}.${c.groupId}"
            artifactId = c.artifact
            version = c.version

            from(components["java"])
            artifact(sourcesJar)
        }
    }

    configure<BintrayExtension> {
        setPublications(c.artifact)

        user = c.username
        key = c.apiKey

        pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
            repo =                  c.groupName
            name =                  "${c.bintrayGroup}.${c.artifact}"
            desc =                  c.description
            websiteUrl =            c.siteUrl
            vcsUrl =                c.gitUrl
            setLicenses(            * c.lics.map { it.toString() }.toTypedArray())
            dryRun =                false
            publish =               true
            override =              c.override
            publicDownloadNumbers = c.publicDownloadNumber

            version(delegateClosureOf<BintrayExtension.VersionConfig> {
                desc = c.description
                released = Date().toString()
            })
        })
    }
}

// region Private Utils
/** Configures the [publishing][org.gradle.api.publish.PublishingExtension] extension */
@Suppress("UnstableApiUsage")
fun Project.publishing(configure: PublishingExtension.() -> Unit): Unit =
        (this as ExtensionAware).extensions.configure("publishing", configure)

/** Retrieves the [sourceSets][org.gradle.api.tasks.SourceSetContainer] extension */
val Project.sourceSets: SourceSetContainer get() =
    (this as ExtensionAware).extensions.getByName("sourceSets") as SourceSetContainer
// endregion
