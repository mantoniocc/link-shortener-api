# --- Stage 1: Build the application ---
# Use a Maven image that has a full JDK to build our application
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code and build the application
COPY src ./src
RUN mvn package -DskipTests


# --- Stage 2: Create the final, smaller image ---
# Use a slim JRE image for a smaller and more secure final container
FROM openjdk:17-jdk-slim

# Set the working dirctory
WORKDIR /app

# Copy the built .jar file from the 'builder' stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
