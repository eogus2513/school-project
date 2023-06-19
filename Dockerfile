# 베이스 이미지 설정
FROM openjdk:17-slim as builder

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 빌드 파일을 Docker 이미지로 복사
COPY . .

# 프로젝트를 빌드하여 실행 가능한 JAR 파일 생성
RUN ./gradlew build -x test --parallel --configure-on-demand

# 실행 단계에서 사용할 베이스 이미지 설정
FROM eclipse-temurin:17-jre-focal as runner

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 이미지에서 생성한 JAR 파일을 실행 이미지로 복사
COPY --from=builder /app/build/libs/*.jar /server.jar

EXPOSE 8080

# 서버 실행
ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "/server.jar"]