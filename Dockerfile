FROM openjdk:latest
EXPOSE 8080
ADD target/notes.jar notes.jar
ENTRYPOINT ["java", "-jar", "/notes.jar"]