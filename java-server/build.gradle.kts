plugins {
    java
    id("org.openapi.generator") version "5.1.0"
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
}

buildscript {
    repositories {
        mavenCentral()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")

    // dependencies for stubs
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("org.openapitools:jackson-databind-nullable:0.2.1")
    implementation("org.springframework.boot:spring-boot-starter-web:2.3.3.RELEASE")
    implementation("io.swagger:swagger-annotations:1.6.2")
}

val generatedStubsDir = "$buildDir/server-stubs"
tasks.getByName("compileJava").dependsOn("openApiGenerate")
openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("${rootProject.projectDir}/src/main/resources/assets-and-dictionary.yaml")
    outputDir.set(generatedStubsDir)
    apiPackage.set("com.smartling.connectors.provider.server.java.api")
    modelPackage.set("com.smartling.connectors.provider.server.java.model")
    configOptions.put("useTags", "true")

    // generates service interfaces that are called from generated controllers
    configOptions.put("delegatePattern", "true")

    // @lombok.Builder makes creating response DTOs more manageable
    // @lombok.NoArgsConstructor helps jackson to deserialize parameters
    // @lombok.AllArgsConstructor is needed for combination of Builder and NoArgsConstructor to work
    configOptions.put("additionalModelTypeAnnotations", "@lombok.Builder\n@lombok.NoArgsConstructor\n@lombok.AllArgsConstructor")
}

sourceSets {
    getByName("main") {
        java {
            srcDir("$generatedStubsDir/src/main/java")
            srcDir("$generatedStubsDir/src/main/resources")
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}