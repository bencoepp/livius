FROM registry.bencoepp.de/maven:3.9.6-amazoncorretto-21-debian AS builder
ARG PATH
COPY ${PATH} .
RUN mvn package
FROM registry.bencoepp.de/openjdk:21-slim
COPY --from=builder /target/app.jar /app.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/app.jar"]
