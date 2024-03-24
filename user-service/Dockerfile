FROM maven:3.8.5-openjdk-17 AS maven_build
COPY pom.xml /
RUN mvn dependency:go-offline

COPY /src /src
RUN mvn -f /pom.xml clean package -DskipTests

FROM openjdk:17-jdk-alpine AS builder
COPY --from=maven_build /target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-jdk-alpine
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]



#FROM maven:3.8.4-openjdk-17 AS maven_builder
#
#WORKDIR /app
#
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
#COPY ./src ./src
#RUN mvn package -DskipTests
#
#FROM openjdk:17-jdk-alpine as builder
#COPY target/*.jar application.jar
#RUN java -Djarmode=layertools -jar application.jar extract
#
#FROM openjdk:17-jdk-alpine
#COPY --from=builder dependencies/ ./
#COPY --from=builder snapshot-dependencies/ ./
#COPY --from=builder spring-boot-loader/ ./
#COPY --from=builder application/ ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
