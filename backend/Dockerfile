FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY backend/ /app/

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon -x test

EXPOSE 8080

# 핵심: 포그라운드에서 jar 직접 실행
CMD ["java", "-Xmx350m", "-Dserver.port=8080", "-Dspring.profiles.active=prod", "-jar", "build/libs/backend-0.0.1-SNAPSHOT.jar"]
