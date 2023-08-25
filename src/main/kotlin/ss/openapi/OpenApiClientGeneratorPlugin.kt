package ss.openapi

import org.gradle.api.Plugin
import org.gradle.api.Project
import ss.openapi.extension.GeneratorSettingsExtension
import ss.openapi.task.GenerateTask

class OpenApiClientGeneratorPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            val settings = extensions.create(
                "openApiGeneratorSettings",
                GeneratorSettingsExtension::class.java,
                project
            )
            tasks.apply {
                register(
                    "generateCode",
                    GenerateTask::class.java
                ).configure {
                    group = pluginGroup
                    description = "Generate code (api, models) using openapi spec"
                }
            }
        }
    }

    companion object {
        const val pluginGroup = "openapi"
    }
}
