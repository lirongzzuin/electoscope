FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY backend/ /app/

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon -x test

EXPOSE 8080

CMD ["java", "-Xmx350m", "-Dspring.profiles.active=prod", "-jar", "build/libs/electoscope-backend-0.0.1-SNAPSHOT.jar"]
