pipeline {
    agent any

    environment {
        // Define environment variables if needed
        DOCKER_HUB_USERNAME = credentials('docker-hub-username')
        DOCKER_HUB_PASSWORD = credentials('docker-hub-password')
    }

    stages {
        stage('Clean and Build') {
            steps {
                script {
                    // Clean and install using Maven
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh 'docker build -t test-docker-spring-boot:4.0 .'

                    // Log in to Docker Hub
                    sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"

                    // Tag the image
                    sh 'docker tag test-docker-spring-boot:4.0 $DOCKER_HUB_USERNAME/test-docker-spring-boot:4.0'

                    // Push the image to Docker Hub
                    sh 'docker push $DOCKER_HUB_USERNAME/test-docker-spring-boot:4.0'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'

            // Clean up: Logout from Docker Hub
            sh 'docker logout'
        }

        failure {
            echo 'Pipeline failed!'

            // Clean up: Logout from Docker Hub
            sh 'docker logout'
        }
    }
}
