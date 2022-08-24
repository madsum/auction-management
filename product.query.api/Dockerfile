FROM openjdk:17
VOLUME /tmp
COPY target/product.query.api-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]