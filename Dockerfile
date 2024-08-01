FROM openjdk:22

WORKDIR /app

COPY target/alm-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties /app/application.properties

EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]