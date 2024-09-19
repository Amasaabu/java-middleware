FROM  ubuntu:20.04 AS build
RUN apt-get update -y && apt-get install maven -y
RUN apt install openjdk-21-jdk-headless -y
COPY . middleware
WORKDIR middleware
ENV DB_USER="root"
ENV DB_URL="jdbc:mysql://host.docker.internal:3306/middlewaredb"
ENV DB_PASS=""
ENV DHMAC_SEC="aG+KbPdSgVkYp3s6v9y$B&E)H@McQfThWmZq4t7w!z%C*F-JaNdRgUkXn2r5u8x/A"
RUN mvn clean install


CMD ["java", "-jar", "target/middleware-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

