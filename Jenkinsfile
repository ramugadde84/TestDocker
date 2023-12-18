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
                    // Log in to Docker Hub using withCredentials
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-username', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                        script {
                            // Convert the username to lowercase
                            def lowercaseUsername = DOCKER_HUB_USERNAME.toLowerCase()

                            // Tag the image with lowercase username
                            bat "docker tag test-docker-spring-boot:4.0 ${lowercaseUsername}/test-docker-spring-boot:4.0"

                            // Log in to Docker Hub
                            bat "docker login -u ${lowercaseUsername} -p ${DOCKER_HUB_PASSWORD}"

                            // Push the image to Docker Hub
                            bat "docker push ${lowercaseUsername}/test-docker-spring-boot:4.0"
                        }
                    }
                }
            }
        }
    }
    post {
            success {
                echo 'Pipeline succeeded!'
                // Clean up: Logout from Docker Hub
                bat 'docker logout'
            }

            failure {
                echo 'Pipeline failed!'

                // Clean up: Logout from Docker Hub
                bat 'docker logout'
            }
        }
}
