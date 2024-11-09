FROM  ubuntu:20.04 AS build
RUN apt-get update -y && apt-get install maven -y
RUN apt install openjdk-21-jdk-headless -y
WORKDIR app
COPY pom.xml .

RUN mvn clean install -DskipTests
COPY . .

FROM openjdk:21-jdk-slim
COPY --from=build /app/target/middleware-0.0.1-SNAPSHOT.jar /app/middleware.jar
WORKDIR /app
EXPOSE 8080

CMD ["java", "-jar", "middleware.jar"]


