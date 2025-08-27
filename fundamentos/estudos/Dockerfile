FROM openjdk:10 AS builder

WORKDIR /app

COPY Main.java .

RUN javac Main.java

FROM ubuntu/jre:21-24.04_stable

WORKDIR /app

COPY --from=builder ./app/Main.class ./Main.class

ENTRYPOINT ["java", "Main"]