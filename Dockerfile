# Etapa 1: Construção da aplicação
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

# Copia os arquivos do projeto e baixa dependências para cache otimizado
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Executa os testes e gera o JAR
RUN mvn clean package

# Etapa 2: Execução da aplicação
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado na fase de build
COPY --from=build /app/target/*.jar app.jar

# Define a porta que o Spring Boot expõe
EXPOSE 8080

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
