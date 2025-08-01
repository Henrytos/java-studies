FROM maven:3.8.5-openjdk-17-slim AS build

COPY pom.xml .

COPY src ./src

RUN mvn clean install 

FROM eclipse-temurin:24-alpine-3.21

COPY --from=build ./target/api-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT [ "java","-jar","app.jar" ]