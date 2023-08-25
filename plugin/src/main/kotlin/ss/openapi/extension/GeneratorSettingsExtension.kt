package ss.openapi.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.property

open class GeneratorSettingsExtension(project: Project) {

    val specUrl = project.objects.property<String>()

    val clientName = project.objects.property<String>()
}