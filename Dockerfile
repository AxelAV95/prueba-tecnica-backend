FROM eclipse-temurin:17-jre-focal

# JAR_FILE will match the built artifact in target/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

# Healthcheck (optional, works on Docker 18.03+)
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
