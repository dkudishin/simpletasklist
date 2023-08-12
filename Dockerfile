FROM eclipse-temurin:17
EXPOSE 8080 8080
RUN mkdir /myapp
WORKDIR /myapp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]