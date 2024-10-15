FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/desafio-btg-pactual-orderms-0.0.1-SNAPSHOT.jar /app/desafio-btg-pactual.jar

ENTRYPOINT ["java", "-jar", "/app/desafio-btg-pactual.jar"]

