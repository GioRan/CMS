#########################
# Maven packaging stage #
#########################
FROM maven:3.6.3-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


#########################
# Dockerizing stage     #
#########################
FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/*.jar /usr/app/api-gateway.jar
EXPOSE 8000
ENTRYPOINT java -Dspring.profiles.active=docker -jar /usr/app/api-gateway.jar api-gateway