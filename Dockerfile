# Usa uma imagem base leve do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define um argumento para o token do Git, fornecido pelo Render
ARG GIT_TOKEN

# Define o diretório de trabalho
WORKDIR /src

# Clona o repositório (somente se necessário no build)
RUN git clone https://${GIT_TOKEN}@github.com/username/repository.git

# Copia o .jar gerado do repositório clonado (assumindo que ele esteja disponível)
COPY repository/target/simple-products-register-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Executa a aplicação
CMD ["java", "-jar", "app.jar"]