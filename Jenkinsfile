pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Шаг для клонирования репозитория
                git 'https://github.com/aizatgaz/ReqresTestingFramework'
            }
        }
        stage('Build') {
            steps {
                // Шаг для сборки проекта с помощью Maven
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                // Шаг для запуска тестов с помощью Maven
                sh 'mvn test'
            }
        }
    }
}