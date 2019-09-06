@file:Suppress(
        "MemberVisibilityCanBePrivate" // Public APIs
)
package studio.forface.easygradle.dsl

import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaGetter

/*
 * A file containing params for publishing
 * Author: Davide Farella
 */

/**
 * Apply studio.forface.easygradle.dsl.publish script to the given module
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
    var username                by project("", propertyName = "bintray.user")
    var apiKey                  by project("", propertyName = "bintray.apikey")
    var bintrayGroup            by project("")
    var groupId                 by project("")
    var artifact                by project("")
    var groupName               by project(artifact)
    var version                 by project("")
    var description             by project("")
    var siteUrl                 by project("")
    var gitUrl                  by project("")
    internal val developers     by project(mutableListOf<Developer>()) // TODO include in `studio.forface.easygradle.dsl.publish`
    internal val licenses       by project(mutableListOf<License>())
    var override                by project(false, propertyName = "publish.override")
    var publicDownloadNumber    by project(true)
    // endregion

    private operator fun <T: Any> Project.invoke(
            default: T,
            propertyName: String? = null
    ) = object : ReadWriteProperty<PublishConfig, T> {
        private var backValue: T? = null

        /** @see ReadWriteProperty.getValue */
        override fun getValue(thisRef: PublishConfig, property: KProperty<*>) =
                backValue ?: prop(propertyName ?: property.name) ?: default

        /** @see ReadWriteProperty.setValue */
        override fun setValue(thisRef: PublishConfig, property: KProperty<*>, value: T) {
            backValue = value
        }

        private fun prop(name: String): T? {
             val stringValue = findProperty(name)?.toString() ?: return null
            @Suppress("UNCHECKED_CAST") // Cast is checked because of safe operator `as?`
            return when(default) {
                is String -> stringValue as? T
                is Boolean -> stringValue.toBoolean() as? T
                is List<*> -> stringValue.toList()
                else -> throw IllegalArgumentException("'${default::class.simpleName}' is not a supported type")
            }
        }

        private fun String.toList(): T? {
            // FIXME
            return null
        }
    }

    // region Dsl functions
    // region Licenses
    /** @return [License], use `unaryPlus` for add it to the current [PublishConfig] */
    @PublishConfig.Marker
    fun license(block: License.() -> Unit) = License().apply(block)

    /** Add receiver [License] to [PublishConfig.licenses] */
    @PublishConfig.Marker
    operator fun License.unaryPlus() {
        licenses += this
    }
    
    /** Scope for [LicensesBuilder.license] */
    @PublishConfig.Marker
    class LicensesBuilder internal constructor()

    /** Add a set of [License]s to the current [PublishConfig] */
    @PublishConfig.Marker
    fun licenses(block: LicensesBuilder.() -> Unit) {
        LicensesBuilder().apply(block)
    }
    
    /** Create and add a [License] to the current [PublishConfig] */
    @Suppress("unused") // Receiver as scope
    @PublishConfig.Marker
    fun LicensesBuilder.license(block: License.() -> Unit) = +License().apply(block)
    // endregion

    // region Developers
    /** @return [Developer], use `unaryPlus` for add it to the current [PublishConfig] */
    @PublishConfig.Marker
    fun developer(block: Developer.() -> Unit) = Developer().apply(block)
    
    /** Add receiver [Developer] to [PublishConfig.developers] */
    @PublishConfig.Marker
    operator fun Developer.unaryPlus() {
        developers += this
    }
    
    /** Scope for [DevelopersBuilder.developer] */
    @PublishConfig.Marker
    class DevelopersBuilder internal constructor()

    /** Create and add a [License] to the current [PublishConfig] */
    @PublishConfig.Marker
    fun developers(block: DevelopersBuilder.() -> Unit) {
        DevelopersBuilder().apply(block)
    }

    /** Add a set of [Developer]s to the current [PublishConfig] */
    @Suppress("unused") // Receiver as scope
    @PublishConfig.Marker
    fun DevelopersBuilder.developer(block: Developer.() -> Unit) = +Developer().apply(block)
    // endregion

    internal fun validate() {
        licenses.forEach { it.validate() }
        developers.forEach { it.validate() }
        assertStringsNotEmpty(::username, ::apiKey, ::bintrayGroup, ::groupId, ::artifact, ::groupName)
    }
    // endregion

    // region Children
    @PublishConfig.Marker
    class Developer internal constructor() {
        var id: String =        ""
        var name: String =      id
        var email: String =     ""

        internal fun validate() {
            assertStringsNotEmpty(::id, ::name, ::email)
        }

        override fun toString() = "id: $id, name: $name, email: $email"
    }
    @PublishConfig.Marker
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

@Suppress("UnstableApiUsage")
private fun Project.publish(c: PublishConfig) = with(project(":${c.artifact}")) {
    apply(plugin = "com.jfrog.bintray")
    apply(plugin = "maven-publish")

    group = "${c.bintrayGroup}.${c.groupId}"
    version = c.version

    val sourcesJar = tasks.create("sourcesJar", Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
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
            setLicenses(            * c.licenses.map{ it.toString() }.toTypedArray())
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
/**
 * Assert that all of the given [KProperty]s do not refer to an empty string
 *
 * @throws IllegalArgumentException
 * @see paramNotSet
 */
private fun Any.assertStringsNotEmpty(vararg prop: KProperty<String>) = prop.forEach(::assertStringNotEmpty)

/**
 * Assert that the given [KProperty] does not refer to an empty string
 *
 * @throws IllegalArgumentException
 * @see paramNotSet
 */
private fun Any.assertStringNotEmpty(prop: KProperty<String>) {
    val string = prop.javaGetter!!.invoke(this) as String
    if (string.isEmpty()) paramNotSet(this::class, prop)
}

/** @throws IllegalArgumentException */
private fun paramNotSet(kclass: KClass<*>, prop: KProperty<*>): Nothing =
        throw IllegalArgumentException("`${kclass.simpleName}.${prop.name}` has not being set")

/** Configures the [publishing][org.gradle.api.publish.PublishingExtension] extension */
@Suppress("UnstableApiUsage")
fun Project.publishing(configure: PublishingExtension.() -> Unit): Unit =
        (this as ExtensionAware).extensions.configure("publishing", configure)

/** Retrieves the [sourceSets][org.gradle.api.tasks.SourceSetContainer] extension */
val Project.sourceSets: SourceSetContainer get() =
    (this as ExtensionAware).extensions.getByName("sourceSets") as SourceSetContainer
// endregion
