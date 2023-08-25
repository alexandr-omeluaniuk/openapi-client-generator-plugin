plugins {
    id("ss.openapi")

}

openApiGeneratorSettings {
    specUrl.set("https://fakerestapi.azurewebsites.net/swagger/v1/swagger.json")
}