FROM openjdk:21
EXPOSE 8080
ADD target/test-docker-spring-boot.jar test-docker-spring-boot.jar
COPY start.sh /scripts/start.sh
RUN chmod +x /scripts/start.sh
