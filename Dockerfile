# 기본 이미지로 OpenJDK 21 사용
FROM openjdk:21-slim

# 애플리케이션 파일을 이미지에 복사
COPY coupon-live-0.0.1-SNAPSHOT.jar /coupon-live.jar

# 애플리케이션 실행
CMD ["java", "-jar", "/coupon-live.jar"]