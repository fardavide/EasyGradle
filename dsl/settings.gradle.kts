rootProject.name = "EasyGradle-dsl"

include("base")
include("android")

project(":base").name = "dsl"
project(":android").name = "dsl-android"
