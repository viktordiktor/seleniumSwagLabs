pipeline {
    agent any
    tools {
        maven 'maven-jenkins'
    }
    stages {
        stage("clone repo") {
            steps {
                git url: 'https://github.com/viktordiktor/seleniumSwagLabs'
            }
        }
        stage("run tests") {
            steps {
                withMaven {
                    sh "mvn clean test"
                }
            }
        }
        stage("allure reports") {
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