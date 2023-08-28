# Use the official Playwright Docker image as the base image
FROM mcr.microsoft.com/playwright:v1.36.0-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the entire Maven project to the container
COPY . .

# Build the Maven project
#RUN mvn clean install

# Set an entry point to run your Playwright Java class
#CMD ["java", "-cp", "target/*", "com.example.YourPlaywrightJavaClass"]

# EXAMPLES
# Build the Docker image
# docker build -t playwright-maven-image .

# Run a container from the created image
# docker run -it playwright-maven-image