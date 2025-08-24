FROM maven:3.8.5-openjdk-17-slim AS build

COPY . .

RUN mvn clean install -DskipTests

FROM ubuntu/jre:21-24.04_stable

WORKDIR /app

COPY --from=build ./target/api-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT [ "java","-jar","app.jar" ]