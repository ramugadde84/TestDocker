pipeline {
    agent any

    environment {
        // Define environment variables if needed
        DOCKER_HUB_USERNAME = credentials('docker-hub-username')
        DOCKER_HUB_PASSWORD = credentials('docker-hub-password')
    }

     tools {
            jdk 'JDk21'
            // Specify the Maven installation configured in Jenkins
            maven 'Maven'
        }

    stages {
           stage('Clean and Build') {
                      steps {
                          script {
                              // Clean and install using Maven
                              bat 'mvn clean install'
                          }
                      }
           }

           stage('Build and Push Docker Image') {
               steps {
                   script {
                       // Build Docker image
                       bat 'docker build -t test-docker-spring-boot:4.0 .'

                        echo "Docker Hub Username: $DOCKER_HUB_USERNAME"
                        echo "Docker Hub Password: $DOCKER_HUB_PASSWORD"
                       // Log in to Docker Hub
                       // Log in to Docker Hub using withCredentials
                       withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                           bat "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"
                       }

                       // Tag the image
                       bat 'docker tag test-docker-spring-boot:4.0 $DOCKER_HUB_USERNAME/test-docker-spring-boot:4.0'

                       // Push the image to Docker Hub
                       bat 'docker push $DOCKER_HUB_USERNAME/test-docker-spring-boot:4.0'
                   }
               }
           }

    }


}
