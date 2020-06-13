# EasyGradle

**EasyGradle** is a set of utilities for simply the build configuration.

It's mean to be included in the `buildSrc` module in the following way:

```kotlin
api("studio.forface.easygradle:dsl:$easyGradle")
```
**or**
```kotlin
api("studio.forface.easygradle:dsl-android:$easyGradle")
```



### Features:



* **Easy publishing** on Bintray ( wraps [maven-publish](https://github.com/vanniktech/gradle-maven-publish-plugin) )

  Support Jvm, Android and Multiplatform

  Only apply to your gradle and use `./gradlew uploadArchives`

  ```kotlin
  // build.gradle.kts
  ...
  publish()
  ```

  * It **fetches params from your `Project`'s properties** ( `group`, `name`, `version`, etc or declared in `gradle.properties` file - see [template](https://github.com/4face-studi0/EasyGradle/blob/master/gradle.properties) - relative to the module or root )

  * or **fetches from environment variables**

  * or **declared in DSL style**

    ```kotlin
    publish {
      artifact = "dsl"
      developers {
        developer {
          name = "Davide"
          id = "4face-studi0"
          email = "mail@face.studio"
        }
      }
      ...
    }
    ```

  * a default block could be stored and reused

    ```kotlin
    val config = PublishConfig { project ->
      version = Version(1, 2, Beta, 2, 0)
      ...
    }
    
    publish(config) {
      // optional additional block
    }
    ```

  

* **Library** and **Plugin** dependencies ( check docs for full list )

  ```kotlin
  repositories {
    classpath(`kotlin-gradle-plugin`)
    classpath(`serialization-gradle-plugin`)
    // etc
  }
  
  plugins {
    `android-library`
    `kotlin-android`
    // etc
  }
  
  dependencies {
    implementation(`kotlin-js` version "1.3.50" ) // version is optional
    implementation(`constraint-layout`)
    testImplementation(`mockk`)
    // etc
  }
  ```

  

* External **versions** declaration

  ```kotlin
  fun initVersions() {
    `kotlin version` =        "1.3.50"        // Updated: Aug 22, 2019
    `espresso version` =      "3.2.0"         // Updated: May 30, 2019
    `lifecycle version` =     "2.2.0-alpha03" // Updated: Aug 09, 2019
    `material version` =      "1.1.0-alpha07" // Updated: May 31, 2019
    `android-test version` =  "1.2.0"         // Updated: May 31, 2019
  }
  ```

  This is a good idea to be called at the start of your `buildscript`

  ```kotlin
  buildscript {
    initVersions()
    ...
  }
  ```

  

* **Configuration accessors** DSL

  ```kotlin
  api(
    `kotlin-jdk7`,
    `serialization`,
    // etc
  )
  implementation(
    `kotlin-reflect`,
    `coroutines-android`,
    // etc
  )
  ```



* **exclude extensions**

  ```kotlin
  androidTestImplementation(
    `android-test`,
    `mockk-android` exclude `mockk`,
    `junit4` exclude jUnit5(`any`) and "another:lib"
  )
  ```

  

* **versioning**, it is also compatible with Android for automatically generate `versionName` and `versionCode` from the given *major, minor, channel, patch and build* versions ( see [Version](https://github.com/4face-studi0/EasyGradle/blob/master/dsl/src/main/kotlin/studio/forface/easygradle/dsl/Version.kt) ) 

  It can also be used by following ( avoiding to pass `versionName` and `versionCode` )

  ```kotlin
  android {
    defaultConfig {
      version = Version(1, 2, Beta, 2, 0)
    }
  }
  ```

  

* **Dokka** builder and extensions ( plugin application is built-in )

  ```kotlin
  // build.gradle.kts
  ...
  dokka()
  ```

  or

  ```kotlin
  dokka {
    // this: DokkaTask
    outputFormatType = OutputFormat.Markdown
  }
  ```

  or, similar to `PublishConfig`

  ```kotlin
  val config = DokkaConfig { project ->
    ...
  }
  
  dokka(config)
  ```

  

* *More to come*

