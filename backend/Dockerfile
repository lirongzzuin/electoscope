FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY backend/ /app/

RUN chmod +x ./gradlew
RUN ./gradlew build --no-daemon

EXPOSE 8080

CMD ["./gradlew", "bootRun", "--no-daemon"]
