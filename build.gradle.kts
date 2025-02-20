@file:Suppress("DEPRECATION")

plugins {
    java
    idea
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.liquibase.gradle") version "2.2.0"
    id("org.openapi.generator") version "7.0.0"
}

group = "edu.school21"
version = "0.0.1"

sourceSets["main"].java.srcDir("$buildDir/generated/src/main/java")

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$rootDir/openapi/openapi.yaml")
    val generatedDir = layout.buildDirectory.dir("generated")
    outputDir.set(generatedDir.map { it.asFile.absolutePath })

    apiPackage.set("edu.school21.openapi.api")
    modelPackage.set("edu.school21.openapi.model")

    configOptions.set(
        mapOf(
            "openApiNullable" to "false",
            "interfaceOnly" to "true",
            "useTags" to "true",
            "useJakartaEe" to "true",
            "defaultInterfaces" to "true",
            "useSpringBoot3" to "true",
            "useResponseEntity" to "false",
            "skipDefaultInterface" to "true"
        )
    )
}

liquibase {
    activities.register("main") {
        val dbUrl = project.extra.properties["dbUrl"] as String
        val dbUser = project.extra.properties["dbUser"] as String
        val dbPass = project.extra.properties["dbPass"] as String
        val dbSchemaName = project.extra.properties["dbSchemaName"] as String

        this.arguments = mapOf(
            "verbose" to true,
            "changeLogFile" to "src/main/resources/db/changelog/sql.1.0.0/changelog.yaml",
            "url" to dbUrl,
            "defaultSchemaName" to dbSchemaName,
            "username" to dbUser,
            "password" to dbPass
        )
    }
    runList = "main"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    runtimeOnly("org.postgresql:postgresql:42.7.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.19")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")

    liquibaseRuntime("org.liquibase:liquibase-core:4.23.1")
    liquibaseRuntime("info.picocli:picocli:4.7.5")
    liquibaseRuntime("org.postgresql:postgresql:42.7.2")
}

tasks.named("build") {
    dependsOn("openApiGenerate")
}

tasks.named("compileJava") {
    dependsOn("openApiGenerate")
}

tasks.named("openApiGenerate") {
    mustRunAfter("clean")
}

