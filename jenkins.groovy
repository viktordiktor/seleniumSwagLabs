pipeline {
    agent any
    tools {
        maven 'maven-jenkins'
        jdk 'jdk-jenkins'
    }
    stages {
        stage("test") {
            steps {
                git url: 'https://github.com/viktordiktor/seleniumSwagLabs'
                withMaven {
                    sh "mvn clean test"
                }
            }
        }
        stage("Allure Report") {
            steps {
                script {
                    generateAllure()
                }
            }
        }
    }
}

def generateAllure() {
    allure([
            includeProperties: true,
            jdk              : '',
            properties       : [],
            reportBuildPolicy: 'ALWAYS',
            results          : [[path: 'target/allure-results']]
    ])
}