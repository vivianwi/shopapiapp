plugins {
    java
    idea
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.liquibase.gradle") version "2.2.0"
    id("org.openapi.generator") version "7.2.0"
}

group = "edu.school21"
version = "0.0.1"

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
    inputSpec.set("$rootDir/src/main/resources/openapi.yaml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("edu.school21.openapi.api")
    modelPackage.set("edu.school21.openapi.model")
    configOptions.set(
        mapOf(
            "openApiNullable" to "true",
            "dateLibrary" to "java8",
            "interfaceOnly" to "true",
            "useTags" to "true"
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
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql:42.7.2")
    implementation("org.postgresql:postgresql:42.7.2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    liquibaseRuntime("org.liquibase:liquibase-core:4.23.1")
    liquibaseRuntime("info.picocli:picocli:4.7.5")
    liquibaseRuntime("org.postgresql:postgresql:42.7.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
