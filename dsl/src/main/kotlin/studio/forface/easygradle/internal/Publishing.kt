package studio.forface.easygradle.internal

import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTask
import studio.forface.easygradle.dsl.PublishConfig
import studio.forface.easygradle.dsl.PublishConfigBuilder
import studio.forface.easygradle.dsl.isAndroid
import studio.forface.easygradle.dsl.publishing
import studio.forface.easygradle.internal.PublishType.*

class PublicationsBundle(val sources: Any?)

typealias PublicationsBundleBuilder = Project.() -> PublicationsBundle

@Suppress("FunctionName") // This is meant to be internal, but needed from Android artifact
fun Project._publish(
    baseBlock: PublishConfigBuilder?,
    artifact: String?,
    block: PublishConfigBuilder,
    type: PublishType,
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
    publish(validConfig, type)
}

enum class PublishType {
    MULTI_PLATFORM, ANDROID_ONLY, JVM_ONLY
}

@Suppress("UnstableApiUsage")
private fun Project.publish(c: PublishConfig, type: PublishType) {
    apply(plugin = "maven-publish")
    // apply(plugin = "signing")

    // signing {
    //     useGpgCmd()
    //     sign(publishing.publications)
    // }

    version = c.version

    val javaDocsJar = tasks.create<Jar>("javaDocsJar") {
        tasks.withType<DokkaTask>().firstOrNull()?.let { dokka ->
            dependsOn(dokka)
            from(dokka.outputDirectory)
        }
        archiveClassifier.set("javadoc")
    }

    val emptySourceJar = tasks.create<Jar>("emptySourcesJar") {
        archiveClassifier.set("sources")
    }

    val sourcesJar = tasks.create<Jar>("sourcesJar") {
        from(c.publicationsBundleBuilder(this@publish).sources)
        archiveClassifier.set("sources")
    }

    fun setupPublishing() {
        publishing {

            publications.withType<MavenPublication> {

                pom {
                    name.set(c.name)
                    description.set(c.description)
                    url.set(c.siteUrl)
                    licenses {
                        for (lic in c.lics)
                            license {
                                name.set(lic.name)
                                url.set(lic.url)
                                distribution.set("repo")
                            }
                    }
                    developers {
                        for (dev in c.devs)
                            developer {
                                id.set(dev.id)
                                name.set(dev.name)
                                email.set(dev.name)
                            }
                    }
                    scm {
                        url.set(c.scmUrl)
                        connection.set("scm:git:${c.scmConnection}")
                        developerConnection.set("scm:git:${c.scmDevConnection}")
                    }
                }

                artifact(javaDocsJar)
                if (type == MULTI_PLATFORM) {
                    if (name == "kotlinMultiplatform") artifact(emptySourceJar)
                } else {
                    artifact(sourcesJar)
                    artifact(tasks[if (isAndroid) "bundleReleaseAar" else "jar"])
                }
            }
        }

        repositories {
            val target = c.organization.takeIf { it.isNotBlank() } ?: c.username
            val override = if (c.override) 1 else 0
            val publish = if (c.publish) 1 else 0
            maven(
                "https://api.bintray.com/maven/" +
                    "$target/" +
                    "${c.repo}/" +
                    "${c.artifact}/;" +
                    "publish=$publish;" +
                    "override=$override"
            ) {
                credentials {
                    username = c.username
                    password = c.apiKey
                }
            }
        }
    }

    if (type == ANDROID_ONLY) afterEvaluate { setupPublishing() }
    else setupPublishing()
}
