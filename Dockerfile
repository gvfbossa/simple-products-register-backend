# Usa uma imagem base leve do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /src

# Copia o .jar gerado pelo build para a imagem
COPY target/simple-products-register-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Executa a aplicação
CMD ["java", "-jar", "app.jar"]
