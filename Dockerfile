FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy the Maven configuration files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make the mvnw script executable
RUN chmod +x ./mvnw

# Download all required dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Create the runtime image
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 