pipeline {
    agent any  // O el agente que prefieras

    stages {
        stage('Checkout') {
            steps {
                // Obtiene el código desde el repositorio
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Ejecuta Maven en la terminal (sh)
                sh 'mvn clean install'
            }
        }
    }
}
