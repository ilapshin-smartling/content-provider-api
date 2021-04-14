plugins {
    kotlin("jvm") version "1.4.32"
}

subprojects {
    buildscript {
        repositories {
            mavenCentral()
        }
    }
    group = "com.smartling.contentprovider.api"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}