plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
}

dependencies {
    implementation(files("../../dsl/base/build/libs/dsl.jar"))
}
