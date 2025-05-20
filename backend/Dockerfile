FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY backend/ /app/

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

EXPOSE 8080
CMD ["./gradlew", "bootRun", "--no-daemon"]
