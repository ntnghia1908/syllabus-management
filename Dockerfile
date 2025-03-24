FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy the Maven configuration files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
# Copy module pom files
COPY syllabus-core/pom.xml syllabus-core/
COPY syllabus-data/pom.xml syllabus-data/
COPY syllabus-service/pom.xml syllabus-service/
COPY syllabus-web/pom.xml syllabus-web/

# Make the mvnw script executable
RUN chmod +x ./mvnw

# Download all required dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY syllabus-core/src syllabus-core/src
COPY syllabus-data/src syllabus-data/src
COPY syllabus-service/src syllabus-service/src
COPY syllabus-web/src syllabus-web/src

# Build the application
RUN ./mvnw package -DskipTests

# Create the runtime image
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/syllabus-web/target/syllabus-web-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 