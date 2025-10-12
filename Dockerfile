FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY build/libs/*.jar app.jar

ENV DB_URL=jdbc:postgresql://localhost:5432/qflow \
    DB_USER=root \
    DB_PASSWORD=root \
    JWT_SECRET=mysecretkey

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
