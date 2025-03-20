pipeline {
    agent any
    tools {
        maven 'mvn'  // nombre que configuraste en Global Tool Configuration
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
