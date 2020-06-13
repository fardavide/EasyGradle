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
val INPUT = File("${rootDir.parentFile.path}${sep}dsl${sep}src${sep}main${sep}kotlin${sep}studio${sep}forface${sep}easygradle${sep}dsl")
val OUTPUT = File("${rootDir.path}${sep}src${sep}main${sep}kotlin")

val matchName = { name: String -> TO_COPY.any { it in name } }

val files = INPUT
    .listFiles { file, name -> file.isDirectory || matchName(name) }.orEmpty()
    .flatMap { subFile ->
        when {
            subFile.isDirectory -> subFile.listFiles { _, name -> matchName(name) }.orEmpty().toList()
            matchName(subFile.name) -> listOf(subFile)
            else -> listOf()
        }
    }

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
