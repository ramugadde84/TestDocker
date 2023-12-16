pipeline {
    agent any

    environment {
        // Define environment variables if needed
        DOCKER_HUB_USERNAME = credentials('docker-hub-username')
        DOCKER_HUB_PASSWORD = credentials('docker-hub-password')
    }

     tools {
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
