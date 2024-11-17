# Use an official Node image to build the frontend
FROM node:18-alpine AS frontend-build

# Set the working directory for frontend
WORKDIR /frontend

# Copy the frontend code
COPY ./Frontend/package*.json ./
RUN npm install
COPY ./Frontend/ .

# Build the frontend
RUN npm run build

# Use an official OpenJDK runtime as the base image for Spring Boot
FROM openjdk:17-jdk-slim AS backend-build

# Install required packages (including xargs)
RUN apt-get update && apt-get install -y --no-install-recommends \
    findutils \
    bash \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory for the backend
WORKDIR /app

# Copy the rest of the Spring Boot source code
COPY Backend /app

# Copy the frontend build into Spring Boot's static resources folder
COPY --from=frontend-build /frontend/dist /app/src/main/resources/static

# Download dependencies
RUN ./gradlew build -x test --no-daemon

# RUN ./gradlew flywayMigrate -Pflyway.url='jdbc:postgresql://db:5432/restaurant_manager'

# Build the Spring Boot application
RUN ./gradlew bootJar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/build/libs/Backend-1.0.0-PRODUCTION.jar"]
