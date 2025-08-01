FROM ubuntu:22.04 AS tests

RUN apt update

RUN apt install openjdk-17-jdk -y

COPY . .

RUN apt install maven -y

RUN mvn clean install 

RUN mvn test



FROM ubuntu:22.04 AS build

RUN apt update

RUN apt install openjdk-17-jdk -y

COPY . .

RUN apt install maven -y

RUN mvn clean install -DskipTests

FROM eclipse-temurin:24-alpine-3.21

COPY --from=build ./target/api-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT [ "java","-jar","app.jar" ]

