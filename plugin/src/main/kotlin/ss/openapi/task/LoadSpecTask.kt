package ss.openapi.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.property

open class LoadSpecTask : DefaultTask() {

    val inputSpec = project.objects.property<String>()

    @TaskAction
    fun doWork() {
        val url = inputSpec.get()
        logger.info("Upload spec from URL [$url]")

    }
}