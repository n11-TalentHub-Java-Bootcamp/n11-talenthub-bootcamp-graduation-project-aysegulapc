FROM postgres
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_DB n11bootcamp

FROM openjdk:11
ARG JAR_FILE=target/n11-talenthub-bootcamp-graduation-project-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]