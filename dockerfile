FROM openjdk:17-jdk-slim AS build
WORKDIR /app

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle/ gradle/
COPY gradlew .
RUN ./gradlew --no-daemon dependencies
COPY . .
RUN mkdir -p /root/.gradle && \
    chmod -R 777 /root/.gradle && \
    ./gradlew clean build -x test

    
FROM liquibase/liquibase:latest
COPY ./src/main/resources/db/changelog /db/changelog

FROM openjdk:17-jdk-slim
COPY --from=build /app/build/libs/*.jar app.jar


EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
