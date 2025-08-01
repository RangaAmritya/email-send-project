FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]