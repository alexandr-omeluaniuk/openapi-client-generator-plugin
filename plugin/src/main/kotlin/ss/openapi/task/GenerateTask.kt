package ss.openapi.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.property
import org.openapitools.codegen.DefaultGenerator
import org.openapitools.codegen.config.CodegenConfigurator
import ss.openapi.SPEC_FILE_DIR
import ss.openapi.SPEC_FILE_NAME
import java.io.File

open class GenerateTask : DefaultTask() {

    @Input
    val clientName = project.objects.property<String>()

    @TaskAction
    fun doWork() {
        val clientNameValue = clientName.get()
        val basePackage = PACKAGE_NAME_PATTERN.format(clientNameValue)
        val outputDir = File(project.buildDir.path + "/generated")
        if (outputDir.exists()) {
            outputDir.deleteRecursively()
        }
        val configurator = CodegenConfigurator()
        configurator.setInputSpec(project.projectDir.path + SPEC_FILE_DIR + "/" + SPEC_FILE_NAME)
        configurator.setGeneratorName("java")
        configurator.setOutputDir(outputDir.path)
        configurator.setPackageName(basePackage)
        configurator.setApiPackage("$basePackage.api")
        configurator.setModelPackage("$basePackage.model")
        configurator.setAdditionalProperties(
            mapOf(
                "dateLibrary" to "java8",
                "library" to "native",
                "openApiNullable" to "false",
                "useJakartaEe" to "true"
            )
        )
        DefaultGenerator(false).opts(
            configurator.toClientOptInput()
        ).generate()
    }

    companion object {
        const val PACKAGE_NAME_PATTERN = "ss.openapi.client.%s"
    }
}