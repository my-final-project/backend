FROM amazoncorretto:21
LABEL authors="juliane.maran"

WORKDIR /app

COPY target/pet-flow-cloud-0.0.1-SNAPSHOT.jar /app/pet-flow-cloud-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/pet-flow-cloud-0.0.1-SNAPSHOT.jar"]