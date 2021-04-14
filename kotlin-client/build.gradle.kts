plugins {
    java
    kotlin("jvm")
    id("org.openapi.generator") version "5.1.0"
}

val generatedSourcesDir = "$buildDir/generated/openapi"

repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // generated client dependencies
    val kotlinVersion = "1.4.32"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("com.squareup.moshi:moshi-kotlin:1.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
}

tasks.getByName("compileKotlin").dependsOn("openApiGenerate")
openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("${rootProject.projectDir}/src/main/resources/assets-and-dictionary.yaml")
    outputDir.set(generatedSourcesDir)
    apiPackage.set("com.smartling.connectors.provider.client.api")
    packageName.set("com.smartling.connectors.provider.client.invoker")
    modelPackage.set("com.smartling.connectors.provider.client.model")
    configOptions.put("enumPropertyNaming", "UPPERCASE") //fixes wRONG eNUM cAPITALIZATION

}

sourceSets {
    getByName("main") {
        java {
            srcDir("$generatedSourcesDir/src/main/kotlin")
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}