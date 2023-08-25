rootProject.name = "openapi-client-generator-plugin"

includeBuild("plugin")
includeBuild("plugin-tests")

pluginManagement {
    includeBuild("plugin")
}
