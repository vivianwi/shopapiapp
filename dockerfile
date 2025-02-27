FROM openjdk:17-jdk-slim AS app
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle/ gradle/
COPY gradlew ./
COPY wait-for-it.sh ./
RUN chmod +x wait-for-it.sh
RUN chmod +x gradlew && ./gradlew --no-daemon dependencies
COPY . .
RUN mkdir -p /root/.gradle && chmod -R 777 /root/.gradle

FROM liquibase/liquibase:latest AS liquibase
WORKDIR /liquibase
COPY /src/main/resources/db db
USER root
COPY wait-for-it.sh ./
RUN chmod +x wait-for-it.sh
COPY ./src/main/resources/db/changelog /db/changelog:rw