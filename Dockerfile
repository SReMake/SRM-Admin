FROM gradle:8.12-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle --refresh-dependencies && gradle bootJar

FROM eclipse-temurin:17-jre-alpine AS runner

ARG ACTIVE_PROFILE=prod

WORKDIR /app

COPY --from=build /app/app/build/libs/app-0.0.1-SNAPSHOT.jar ./app.jar
COPY --from=build /app/app/src/main/resources ./

ENV ACTIVE_PROFILE=${ACTIVE_PROFILE}

EXPOSE 8080

CMD ["java", "-jar", "app.jar" ,"--spring.profiles.active=$ACTIVE_PROFILE"]