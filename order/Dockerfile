FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/order-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} order-app.jar
ENTRYPOINT ["java","-jar","/order-app.jar"]