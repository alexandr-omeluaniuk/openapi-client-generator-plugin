plugins {
    id("ss.openapi")
    `java-library`
    kotlin("jvm") version "1.8.20"
}

openApiGeneratorSettings {
    specUrl.set("https://fakerestapi.azurewebsites.net/swagger/v1/swagger.json")
    clientName.set("fake")
}