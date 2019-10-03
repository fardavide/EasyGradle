# EasyGradle

**EasyGradle** is a set of utilities for simply the build configuration.

It's mean to be included in the `buildSrc` module in the following way:

```kotlin
api("studio.forface.easygradle:dsl-android:$easyGradle")
```

### Features:

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

* External **versions** declaration ( or default ones set by the library )

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

* **Dependencies declaration** DSL

  Check [related file](https://github.com/4face-studi0/EasyGradle/blob/master/dsl/src/main/kotlin/studio/forface/easygradle/dsl/Dependency.kt), to be documented here

* **Android versioning** class for automatically generate `versionName` and `versionCode` from the given *major, minor, channel, patch and build* versions ( see [Version](https://github.com/4face-studi0/EasyGradle/blob/master/dsl-android/src/main/kotlin/studio/forface/easygradle/dsl/android/Version.kt) ) 

  It can also be used by following ( avoiding to pass `versionName` and `versionCode` )

  ```kotlin
  android {
    defaultConfig {
      version = Version(1, 2, Beta, 2, 0)
    }
  }
  ```

* **Dokka script**

  ```kotlin
  // build.gradle.kts
  ...
  dokka() // or `dokkaAndroid()`
  ```

  * It has default params

  * other params could be declared in `gradle.properties` file ( see [template](https://github.com/4face-studi0/EasyGradle/blob/master/gradle.properties.template) )

  * or inside the block 

    ```kotlin
    dokka {
      apiVersion = ...
      outputDirectory = ...
      ...
    }
    ```

* **Publishing script** for Bintray

  ```kotlin
  // build.gradle.kts
  ...
  publish()
  ```

  * It has default params

  * other params could be declared in `gradle.properties` file ( see [template](https://github.com/4face-studi0/EasyGradle/blob/master/gradle.properties.template) )

  * or inside the block

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

  * a default block could be created

    ```kotlin
    val defaultPublishConfig = publishConfig {
      androidVersion = Version(1, 2, Beta, 2, 0)
      ...
    }
    
    publish(baseBlock = defaultPublishConfig) {
      ...
    }
    ```

* *More to come*

