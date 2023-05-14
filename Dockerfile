FROM adoptopenjdk/openjdk11:alpine-jre

LABEL authors="david.pico"

# .jar file
ARG JAR_FILE=target/*.jar

# Copy jarfile to /
COPY ${JAR_FILE} atsistemas.jar

# Run de application
ENTRYPOINT ["java","-jar","/atsistemas.jar"]
