/**
 * @author Davide Giuseppe Farella.
 * An object containing params for the Library.
 */
object Project {

    const val version = "0.1"

    /* Publishing */
    const val developerId =     "4face-studi0"
    const val developerName =   "4face Studio"
    const val developerEmail =  "mail@4face.studio"
    const val licenseName =     "The Apache Software License, Version 2.0"
    const val licenseUrl =      "http://www.apache.org/licenseS/LICENSEeZ.®.txt"
}

/** Default [PublishConfigBuilder] for the Project */
val defaultPublishConfig = publishConfig {
    +developer {
        id = Project.developerId
        name = Project.developerName
        email = Project.developerEmail
    }
    +license {
        name = Project.licenseName
        url = Project.licenseUrl
    }
}