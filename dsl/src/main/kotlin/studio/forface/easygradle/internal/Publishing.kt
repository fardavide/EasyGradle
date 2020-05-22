package studio.forface.easygradle.internal

import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.delegateClosureOf
import org.gradle.kotlin.dsl.get
import studio.forface.easygradle.dsl.PublishConfig
import studio.forface.easygradle.dsl.PublishConfigBuilder
import studio.forface.easygradle.dsl.isAndroid
import studio.forface.easygradle.dsl.publishing
import java.util.Date

class PublicationsBundle(val sources: Any)

typealias PublicationsBundleBuilder = Project.() -> PublicationsBundle

@Suppress("FunctionName") // This is meant to be internal, but needed from Android artifact
fun Project._publish(
    baseBlock: PublishConfigBuilder?,
    artifact: String?,
    block: PublishConfigBuilder,
    publicationsBundleBuilder: PublicationsBundleBuilder
) {
    val project = this
    val validConfig = PublishConfig(this).apply {
        baseBlock?.let { this.baseBlock(project) }
        artifact?.let { this.artifact = it }
        this.block(project)
        this.publicationsBundleBuilder = publicationsBundleBuilder
        validate()
    }
    publish(validConfig)
}

private fun PublishConfig.projectFor(project: Project) =
        if (projectName?.isBlank() == false) project.project(":$projectName") else project

@Suppress("UnstableApiUsage")
private fun Project.publish(c: PublishConfig) = with(c.projectFor(this)) {
    apply(plugin = "com.jfrog.bintray")
    apply(plugin = "maven-publish")

    group = "${c.bintrayGroup}.${c.groupId}"
    version = c.version

    afterEvaluate {
        publishing {
            publications.create<MavenPublication>(c.artifact) {
                groupId = "${c.bintrayGroup}.${c.groupId}"
                artifactId = c.artifact
                version = c.version

                val bundle = c.publicationsBundleBuilder(this@with)
                val sourcesJar = tasks.create("sourcesJar", Jar::class) {
                    archiveClassifier.set("sources")
                    from(bundle.sources)
                }

                artifact(tasks[if (isAndroid) "bundleReleaseAar" else "jar"])
                artifact(sourcesJar)
            }
        }

        configure<BintrayExtension> {
            setPublications(c.artifact)

            user = c.username
            key = c.apiKey

            pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
                repo = c.groupName
                name = "${c.bintrayGroup}.${c.artifact}"
                desc = c.description
                websiteUrl = c.siteUrl
                vcsUrl = c.gitUrl
                setLicenses(* c.lics.map { it.toString() }.toTypedArray())
                dryRun = false
                publish = true
                override = c.override
                publicDownloadNumbers = c.publicDownloadNumber

                version(delegateClosureOf<BintrayExtension.VersionConfig> {
                    desc = c.description
                    released = Date().toString()
                })
            })
        }
    }
}
