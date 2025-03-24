FROM openjdk:21-jre-slim
COPY versioned-0.jar /app/versioned.jar
CMD ["java", "-jar", "/app/versioned.jar"]