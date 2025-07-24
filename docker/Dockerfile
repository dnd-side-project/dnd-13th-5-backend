FROM openjdk:17-jdk

WORKDIR /dnd_13th_5team_dev

COPY build/libs/*SNAPSHOT.jar /dnd_13th_5team_dev.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/dnd_13th_5team_dev.jar"]