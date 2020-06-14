@file:Suppress("VariableNaming", "PropertyName")

/*
 * Copy files from `dsl` module to `buildSrc`
 */

val TO_COPY = arrayOf(
    "ConfigReadWriteProperty",
    "dokka",
    "extensions",
    "publishing",
    "utils"
)

val LINES_TO_EXCLUDE = arrayOf(
    "package studio.forface.easygradle",
    "import studio.forface.easygradle",
    "var PublishConfig.version: Version",
    "get() = throw UnsupportedOperationException()",
    "set(value) { versionName = value.versionName }"
)

val sep: String = File.separator
val INPUT = File("${rootDir.parentFile.path}${sep}dsl${sep}src${sep}main${sep}kotlin${sep}studio${sep}forface${sep}easygradle")
val OUTPUT = File("${rootDir.path}${sep}src${sep}main${sep}kotlin")

fun File.children(): List<File> {
    return listFiles().orEmpty().flatMap {
        if (it.isDirectory) it.children()
        else listOf(it)
    }
}

val files = INPUT.children().filter { file -> TO_COPY.any { it in file.name } }

println("Copying file: ${files.joinToString { it.path }}")

for (file in files) {
    File(OUTPUT, file.name)
        // clean file
        .also { it.writeText("") }
        .bufferedWriter()
        .use { writer ->

            file.forEachLine { line ->
                if (LINES_TO_EXCLUDE.none { it in line }) {
                    writer.appendln(line)
                }
            }
        }
}
