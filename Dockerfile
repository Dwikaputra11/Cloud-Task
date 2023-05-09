#FROM openJdk:17-oracle as build
#WORKDIR /app
#COPY . .
#RUN  mvn package
#EXPOSE 8080
#ADD target/tcc_task.jar tcc_task.jar
#ENTRYPOINT ["java","-jar","/tcc_task.jar"]
# Set base image to the official Java image
FROM openjdk:17-oracle as build

# Set working directory
WORKDIR /app
./
# Copy the source code to the working directory
COPY . .

# Build the project
RUN chmod +x mvnw
RUN ./mvnw package

# Set the command to run the app
CMD ["java", "-jar", "./target/tcc_task-0.0.1-SNAPSHOT.jar"]