[plugin](../../index.md) / [studio.forface.easygradle.publish](../index.md) / [org.gradle.api.Project](index.md) / [publish](./publish.md)

# publish

`fun Project.publish(config: `[`PublishConfigBuilder`](../-publish-config-builder.md)`? = null, artifact: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, block: `[`PublishConfigBuilder`](../-publish-config-builder.md)` = {}): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Apply publish script to the receiver [Project](#)

Params for [EasyPublishExtension](../-easy-publish-extension/index.md) can be set:

* programmatically
* in `gradle.properties`
* in Environment variable

### Parameters

`artifact` - Optional [EasyPublishExtension.artifact](../-easy-publish-extension/artifact.md) for the [EasyPublishExtension](../-easy-publish-extension/index.md), this is useful when we have a stored
common [EasyPublishExtension](../-easy-publish-extension/index.md) for the project and we want to apply it for a single module

`config` - Optional Lambda previously created by [EasyPublishExtension](../-easy-publish-extension/index.md) for have a base setup for [EasyPublishExtension](../-easy-publish-extension/index.md)

`block` - Lambda for setup [EasyPublishExtension](../-easy-publish-extension/index.md)

**See Also**

[EasyPublishExtension](../-easy-publish-extension/index.md)

