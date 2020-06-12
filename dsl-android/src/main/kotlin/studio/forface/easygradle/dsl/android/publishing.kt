package studio.forface.easygradle.dsl.android

import org.gradle.api.Project
import studio.forface.easygradle.dsl.*

@Deprecated("Use 'publish'", ReplaceWith("publish(baseBlock, artifact, block)"))
fun Project.publishAndroid(
    baseBlock: PublishConfigBuilder? = null,
    artifact: String? = null,
    block: PublishConfigBuilder = {}
) {
    publish(baseBlock, artifact, block)
}
