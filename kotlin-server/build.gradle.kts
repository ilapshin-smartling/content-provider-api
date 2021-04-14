import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("org.openapi.generator") version "5.1.0"

    // generated plugins
    val kotlinVersion = "1.4.32" // version changed from 1.3.10
    id("org.jetbrains.kotlin.jvm") // version removed
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("org.springframework.boot") version "2.4.1" // version changed from 2.2.0.M3
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
}

buildscript {
    repositories {
        mavenCentral()
    }
}
val generatedSourcesDir = "$buildDir/server-stubs"

repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // dependencies for stubs
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
}

tasks.getByName("compileKotlin").dependsOn("openApiGenerate")
openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("${rootProject.projectDir}/src/main/resources/assets-and-dictionary.yaml")
    outputDir.set(generatedSourcesDir)
    apiPackage.set("com.smartling.connectors.provider.server.kotlin.api")
    modelPackage.set("com.smartling.connectors.provider.server.kotlin.model")
    configOptions.put("useTags", "true")

    //fixes wRONG eNUM cAPITALIZATION
    configOptions.put("enumPropertyNaming", "UPPERCASE")

    // generates service interfaces that are called from generated controllers
    configOptions.put("serviceInterface", "true")
}

sourceSets {
    getByName("main") {
        java {
            srcDir("$generatedSourcesDir/src/main/kotlin")
            srcDir("$generatedSourcesDir/src/main/resources")
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}


val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}