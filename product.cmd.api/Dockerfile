FROM openjdk:15
VOLUME /tmp
COPY target/product.cmd.api-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]