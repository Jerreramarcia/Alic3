FROM openjdk:21-slim
COPY target/versioned-0.jar /app/versioned.jar
CMD ["java", "-jar", "/app/versioned.jar"]
