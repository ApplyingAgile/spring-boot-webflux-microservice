FROM openjdk:8-jdk-alpine
ARG BUILD_VERSION
ADD build/docker/examplems-${BUILD_VERSION}.jar app.jar
ENV MONGODB_URI mongodb://springboot-mongo:27017/springmongo-demo
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=${MONGODB_URI}","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]