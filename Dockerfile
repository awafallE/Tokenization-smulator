# ==========================
# Stage 1 : Build the project
# ==========================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Equivalent to: cd /app
WORKDIR /app

# Copy only the pom.xml first
COPY pom.xml .

# Download Maven dependencies
RUN mvn dependency:go-offline

# Copy the rest of the project
COPY . .

# Build the application and create the JAR
RUN mvn clean package -DskipTests


# ==========================
# Stage 2 : Run the application
# ==========================
FROM eclipse-temurin:21-jre-alpine

# Working directory inside the container
WORKDIR /app

# Copy only the JAR produced in stage 1
COPY --from=builder /app/target/*.jar app.jar

# Tell Docker the application listens on port 8080
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]