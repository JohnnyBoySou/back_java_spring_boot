# Usa a imagem base do OpenJDK 21
FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp

# Copia o JAR do target para dentro do container
COPY target/*.jar app.jar

# Define o comando de inicialização
ENTRYPOINT ["java", "-jar", "/app.jar"]
