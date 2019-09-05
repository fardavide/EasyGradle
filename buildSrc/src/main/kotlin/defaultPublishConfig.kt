import studio.forface.easygradle.dsl.publishConfig
import studio.forface.easygradle.dsl.PublishConfigBuilder

/** Default [PublishConfigBuilder] for the Project */
val defaultPublishConfig = publishConfig {
    username = it.typedProperty("bintray.user")
    apiKey = it.typedProperty("bintray.apikey")
    bintrayGroup = Project.bintrayGroup
    groupId = Project.groupId
    // artifact set later
    name = Project.name
    version = Project.version
    description = Project.description
    siteUrl = Project.siteUrl
    gitUrl = Project.gitUrl
    override = true
    + developer {
        id = Project.developerId
        name = Project.developerName
        email = Project.developerEmail
    }
    + license {
        name = Project.licenseName
        url = Project.licenseUrl
    }
}

private inline fun <reified T> org.gradle.api.Project.typedProperty(s: String) = project.findProperty(s) as T
