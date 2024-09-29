FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY #[[\$]]#{JAR_FILE} apicoletorandroid-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/apicoletorandroid-0.0.1.jar"]