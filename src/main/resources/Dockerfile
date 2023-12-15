FROM openjdk:21
EXPOSE 8080
ADD target/test-docker-spring-boot.jar test-docker-spring-boot.jar
ENTRYPOINT ["java","-jar","/test-docker-spring-boot.jar"]
