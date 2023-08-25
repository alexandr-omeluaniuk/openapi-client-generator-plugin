package ss.openapi.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.property
import ss.openapi.SPEC_FILE_DIR
import ss.openapi.SPEC_FILE_NAME
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption

open class LoadSpecTask : DefaultTask() {

    @Input
    val specUrl = project.objects.property<String>()

    @TaskAction
    fun doWork() {
        val url = specUrl.get()
        try {
            logger.info("Upload spec from URL [$url]")
            val resourcesDir = File(project.projectDir.path + SPEC_FILE_DIR)
            if (!resourcesDir.exists()) {
                resourcesDir.mkdirs()
            }
            val specFile = File(resourcesDir.path + "/" + SPEC_FILE_NAME)
            downloadFile(URL(url), specFile)
        } catch (e: Exception) {
            logger.warn("Spec file [$url] can not be downloaded", e)
        }
    }

    private fun downloadFile(url: URL, file: File) {
        url.openStream().use { Files.copy(it, file.toPath(), StandardCopyOption.REPLACE_EXISTING) }
    }
}