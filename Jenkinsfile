pipeline {
    agent {
        docker {
            // Imagen Docker con las herramientas necesarias (Maven, Java, etc.)
            image 'maven:3.9.2-eclipse-temurin-17'
            // Montar tu .m2 local (opcional) para cachear dependencias
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -version'
                sh 'mvn clean install'
            }
        }
    }
}
