FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/maestroapi-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xmx4096m","-Xms3072m","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]