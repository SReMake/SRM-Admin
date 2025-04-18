FROM gradle:8.12-jdk17 AS build

WORKDIR /app

COPY . .

RUN gradle --refresh-dependencies && gradle build

FROM ibm-semeru-runtimes:open-17-jre-noble AS runner

ARG ACTIVE_PROFILE=prod

WORKDIR /app

COPY --from=build /app/app/build/libs/app-0.0.1-SNAPSHOT.jar ./app.jar
COPY --from=build /app/app/src/main/resources ./

ENV APP_ENV=${ACTIVE_PROFILE}

EXPOSE 8080

CMD ["java", "-jar", "app.jar" ,"--spring.profiles.active=${APP_ENV}"]