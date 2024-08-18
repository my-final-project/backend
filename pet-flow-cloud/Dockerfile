FROM amazoncorretto:21
LABEL authors="juliane.maran"

WORKDIR /app

COPY target/pet-flow-cloud-0.0.1-SNAPSHOT.jar /app/pet-flow-cloud-0.0.1-SNAPSHOT.jar

RUN mvn clean install

CMD mvn spring-boot:run

ENTRYPOINT ["java", "-jar", "/app/pet-flow-cloud-0.0.1-SNAPSHOT.jar"]