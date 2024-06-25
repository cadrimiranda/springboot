FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x ./mvnw

RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix ./mvnw

RUN ./mvnw dependency:resolve

COPY src ./src

ENV DB_IP_PORT=${DB_IP_PORT}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV PORT=${PORT}

CMD ["./mvnw", "spring-boot:run", "--debug"]
