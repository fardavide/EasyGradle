import studio.forface.easygradle.dsl.*

fun initVersions() {
    `kotlin version` = "1.4-M3"
    `serialization version` = "0.20.0-${`kotlin version`}"
    `mockK version` = "1.10.0"
}
