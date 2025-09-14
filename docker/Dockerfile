FROM openjdk:21-jdk as builder
WORKDIR /workspace/app

FROM openjdk:21-jdk as builder
WORKDIR /workspace/app

FROM openjdk:21-jdk
WORKDIR /dnd_13th_5team_dev

COPY --from=builder workspace/app/spring-boot-loader/ ./
COPY --from=builder workspace/app/dependencies/ ./
COPY --from=builder workspace/app/snapshot-dependencies/ ./
COPY --from=builder workspace/app/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]