FROM openjdk:17-jdk-alpine

ARG JAR_FOLDER
WORKDIR /opt

ADD ${JAR_FOLDER}/spring-boot-loader/ ./
ADD ${JAR_FOLDER}/dependencies/ ./
ADD ${JAR_FOLDER}/snapshot-dependencies/ ./
ADD ${JAR_FOLDER}/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]