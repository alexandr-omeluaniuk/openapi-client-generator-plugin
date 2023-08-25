package ss.openapi.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.property
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths

open class LoadSpecTask : DefaultTask() {

    @Input
    val specUrl = project.objects.property<String>()

    @TaskAction
    fun doWork() {
        val url = specUrl.get()
        logger.info("Upload spec from URL [$url]")
        val resourcesDir = File(project.projectDir.path + "/src/main/resources")
        if (!resourcesDir.exists()) {
            resourcesDir.mkdirs()
        }
        val specFile = File(resourcesDir.path + "/" + SPEC_FILE_NAME)
        downloadFile(URL(url), specFile)
    }

    private fun downloadFile(url: URL, file: File) {
        url.openStream().use { Files.copy(it, file.toPath()) }
    }

    companion object {
        val SPEC_FILE_NAME = "spec.json"
    }
}