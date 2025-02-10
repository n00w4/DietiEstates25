# Utilizziamo un'immagine leggera di JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Impostiamo la working directory
WORKDIR /app

# Copiamo il file pom.xml e scarichiamo le dipendenze (cache-friendly)
COPY pom.xml ./
RUN apk add --no-cache maven && mvn dependency:go-offline

# Copiamo il codice sorgente e costruiamo l'applicazione
COPY src ./src
RUN mvn package -DskipTests

# Espone la porta su cui gira il server (modifica se necessario)
EXPOSE 8080

# Comando per avviare l'app
CMD ["java", "-jar", "target/backendDietiEstates-0.0.1-SNAPSHOT.jar"]
