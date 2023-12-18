FROM openjdk:21
# Create a 'scripts' folder
EXPOSE 8080
ADD target/test-docker-spring-boot.jar test-docker-spring-boot.jar
CMD ["java", "-jar", "test-docker-spring-boot.jar"]