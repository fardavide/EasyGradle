# EasyGradle

**EasyGradle** is a set of utilities for simply the Gradle configuration.

It includes:

* **Gradle DSL+**
* **EasyPublish on Bintray plugin** for Java, Kotlin & Kotlin Multiplatform!

###### Version 1.x includes also ( few ) utils for Dokka which are not included yet in 2.x - it's still need to be evaluated whether a dedicated package for it is worth to be distributed. [1.x readme here](README_1.x.md)



## DSL+

It's mean to be included in the `buildSrc` module in the following way:

```kotlin
api("studio.forface.easygradle:dsl:$dslVersion")
```
**or**
```kotlin
api("studio.forface.easygradle:dsl-android:$dslVersion")
```



### Features:


* **Library** and **Plugin** dependencies ( check docs for full list )

  ```kotlin
  repositories {
    classpath(`kotlin-gradle-plugin`)
    classpath(`serialization-gradle-plugin`)
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





## EasyPublis plugin for Bintray ( wraps [maven-publish](https://github.com/vanniktech/gradle-maven-publish-plugin) )

Support Jvm, Android and Multiplatform

**Apply with DSL**

```kotlin
plugins {
  id("studio.forface.easy-publish") version easyPublishVersion
}
```

**Apply with legacy**

```kotlin
buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("gradle.plugin.EasyGradle-publish:plugin:0.2.1")
  }
}

apply(plugin = "studio.forface.easy-publish")
```



### Usage

Configure into your gradle and use `./gradlew uploadArchives`

```kotlin
// build.gradle.kts
...
easyPublish {
  // optional config
}
```

* It **fetches params from your `Project`'s properties** ( `group`, `name`, `version`, etc or declared in `gradle.properties` file - see [template](https://github.com/4face-studi0/EasyGradle/blob/master/gradle.properties) - relative to the module or root )

* or **fetches from environment variables**

* or **declared in DSL style**

  ```kotlin
  easyPublish {
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



* *More to come*
