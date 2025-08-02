FROM openjdk:21-jdk-slim

WORKDIR /application

COPY target/*.jar /application/email.jar

ENTRYPOINT ["java", "-jar", "email.jar"]