package ss.openapi.extension

import org.gradle.api.Project
import org.gradle.kotlin.dsl.property

class GeneratorSettingsExtension(project: Project) {

    val specUrl = project.objects.property<String>()
}