import studio.forface.easygradle.dsl.PublishConfigBuilder
import studio.forface.easygradle.dsl.publishConfig

/** Default [PublishConfigBuilder] for the Project */
val defaultPublishConfig = publishConfig {
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
