FROM eclipse-temurin:17-jdk

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 복사
COPY . /app

# Gradle 캐시를 줄이기 위해 명시적 권한 설정 (선택)
RUN chmod +x ./gradlew

# 의존성 설치 및 빌드
RUN ./gradlew build --no-daemon

# 포트 오픈
EXPOSE 8080

# 애플리케이션 실행
CMD ["./gradlew", "bootRun", "--no-daemon"]
