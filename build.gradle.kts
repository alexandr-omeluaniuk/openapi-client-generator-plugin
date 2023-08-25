plugins {
    `kotlin-dsl`
    kotlin("jvm") version "1.8.20"
    id("java-gradle-plugin")
}

group = "ss.openapi"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openapitools:openapi-generator:7.0.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
        create("openApiClientGenerator") {
            id = "ss.openapi"
            implementationClass = "ss.openapi.OpenApiClientGeneratorPlugin"
        }
    }
}