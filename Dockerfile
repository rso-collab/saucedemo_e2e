# =====================================================
# Dockerfile — Java Selenium Test Runner for SauceDemo
# =====================================================

# Use Maven with JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Working directory inside the container
WORKDIR /app

# Copy project files
COPY . .

# Default command: run tests with stage config against remote Selenium Grid
CMD ["mvn", "clean", "test", "-Denv=stage", "-Dbrowser=remote"]
