pipeline {
    agent any
    tools {
        maven 'Mvn'  // nombre que configuraste en Global Tool Configuration
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
