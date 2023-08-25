plugins {
    id("ss.openapi")
    `java-library`
}

openApiGeneratorSettings {
    specUrl.set("https://fakerestapi.azurewebsites.net/swagger/v1/swagger.json")
}