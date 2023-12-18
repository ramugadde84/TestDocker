FROM openjdk:21
# Create a 'scripts' folder
RUN mkdir -p /scripts
EXPOSE 8080
ADD target/test-docker-spring-boot.jar test-docker-spring-boot.jar
COPY start.sh /scripts/start.sh
RUN chmod +x /scripts/start.sh