FROM amazoncorretto:11-alpine
ADD target/stock-api.jar stock-api.jar
EXPOSE 8443
ENTRYPOINT ["java", "-jar", "stock-api.jar"]