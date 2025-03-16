FROM gradle:8.12-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle --refresh-dependencies && gradle app:bootJar

FROM eclipse-temurin:17-jre-alpine AS runner

WORKDIR /app

COPY --from=build /app/app/build/libs/app-0.0.1-SNAPSHOT.jar ./app.jar
COPY --from=build /app/app/src/main/resources/* ./

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]