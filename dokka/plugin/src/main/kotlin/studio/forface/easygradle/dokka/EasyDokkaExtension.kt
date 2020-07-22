package studio.forface.easygradle.dokka

import org.gradle.api.Project
import org.jetbrains.dokka.gradle.DokkaTask
import javax.inject.Inject

@Suppress("UnnecessaryAbstractClass", "MemberVisibilityCanBePrivate")
abstract class EasyDokkaExtension @Inject constructor(project: Project) : DokkaTask()
