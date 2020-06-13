[dsl](../../index.md) / [studio.forface.easygradle.dsl](../index.md) / [org.gradle.api.Project](index.md) / [dokka](./dokka.md)

# dokka

`fun Project.dokka(config: `[`DokkaConfig`](../-dokka-config.md)` = {}, block: `[`DokkaConfig`](../-dokka-config.md)` = {}): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Apply studio.forface.easygradle.dsl.dokka script to the given module
It will apply the Dokka plugin and setup relative [DokkaTask](#)

### Parameters

`config` - base configuration [DokkaConfig](../-dokka-config.md)
Use [DokkaConfig](../-dokka-config.md) function for create a base configuration

`block` -

Lambda for setup [DokkaConfig](../-dokka-config.md)


```
dokka {
outputDirectory = ...
outputFormat = ...
}
```



They also can be used together


```
val baseConfig = DokkaConfig {
outputDirectory = ...
}
dokka(baseConfig) {
outputFormat = ...
}
```

