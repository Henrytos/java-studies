FROM ubuntu:latest AS build

RUN apt update

RUN apt install openjdk-17-jdk -y

COPY . .

RUN apt install -y maven

RUN mvn clean install 

FROM openjdk:26-slim AS production

COPY --from=build ./target/gestao_de_vagas-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]