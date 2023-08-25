package ss.openapi.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.TaskAction
import org.openapitools.codegen.DefaultGenerator
import org.openapitools.codegen.config.CodegenConfigurator
import ss.openapi.SPEC_FILE_DIR
import ss.openapi.SPEC_FILE_NAME

@CacheableTask
open class GenerateTask : DefaultTask() {

    @TaskAction
    fun doWork() {
        val configurator = CodegenConfigurator()
        configurator.setInputSpec(project.projectDir.path + SPEC_FILE_DIR + "/" + SPEC_FILE_NAME)
        DefaultGenerator(false).opts(
            configurator.toClientOptInput()
        ).generate()
    }
}