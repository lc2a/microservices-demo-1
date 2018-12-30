FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/product-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} product-app.jar
ENTRYPOINT ["java","-jar","/product-app.jar"]