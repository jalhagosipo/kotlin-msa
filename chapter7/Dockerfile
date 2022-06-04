FROM openjdk:11-jre
ADD build/libs/*.jar microservice.jar
ENTRYPOINT ["java", "-jar", "microservice.jar"]