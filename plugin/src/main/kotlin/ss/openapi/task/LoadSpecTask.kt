package ss.openapi.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.property
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
        if (!project.buildDir.exists()) {
            project.buildDir.mkdirs()
        }
        downloadFile(URL(url), project.buildDir.path + "/spec.json")
    }

    private fun downloadFile(url: URL, fileName: String) {
        url.openStream().use { Files.copy(it, Paths.get(fileName)) }
    }
}