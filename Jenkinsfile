pipeline {
    tools {
        maven 'Mvn'  // nombre que configuraste en Global Tool Configuration
    }
    agent {
        docker {
            image 'maven:3.9.2-eclipse-temurin-21' // ejemplo de imagen con Maven + JDK 21
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}