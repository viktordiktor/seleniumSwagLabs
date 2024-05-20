pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                git url: 'https://github.com/viktordiktor/seleniumSwagLabs'
                withMaven {
                    sh "mvn clean test"
                }
            }
        }
        stage("allure") {
            generateAllure()
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
