FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY backend/ /app/

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon -x test

EXPOSE 8080

CMD ["sh", "-c", "java -Xmx350m -Dserver.port=8080 -Dspring.profiles.active=prod -jar build/libs/*.jar"]
