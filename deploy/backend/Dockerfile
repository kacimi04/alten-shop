FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./alten-shop-back-0.0.1-SNAPSHOT.jar /app/alten-shop-back-0.0.1-SNAPSHOT.jar
COPY ./application-dev.properties /app/application-dev.properties
ENTRYPOINT ["java", "-jar", "/app/alten-shop-back-0.0.1-SNAPSHOT.jar","--spring.config.location=file:/app/application-dev.properties"]
EXPOSE 8080