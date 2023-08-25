package ss.openapi

import org.gradle.api.Plugin
import org.gradle.api.Project
import ss.openapi.extension.GeneratorSettingsExtension
import ss.openapi.task.GenerateTask
import ss.openapi.task.LoadSpecTask

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
                    "loadSpec",
                    LoadSpecTask::class.java
                ).configure {
                    group = pluginGroup
                    description = "Load spec and save it on success as resource"

                    specUrl.set(settings.specUrl)
                }
                register(
                    "generateCode",
                    GenerateTask::class.java
                ).configure {
                    group = pluginGroup
                    description = "Generate code (api, models) using openapi spec"
                    dependsOn(tasks.getByName("loadSpec"))
                }
            }
        }
    }

    companion object {
        const val pluginGroup = "openapi"
    }
}

const val SPEC_FILE_NAME = "spec.json"
const val SPEC_FILE_DIR = "/src/main/resources"
