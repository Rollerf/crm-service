FROM openjdk:11
ADD "target/crm-service-1.0.0.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]