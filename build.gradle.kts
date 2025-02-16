import org.gradle.kotlin.dsl.support.kotlinCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.amqp:spring-rabbit:3.1.6")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

//tasks.withType<KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs += "-Xjsr305=strict"
//    }
//}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}