pipeline {
    agent none 
    
    tools {
        maven "M3"
    }

    parameters {
        booleanParam defaultValue: true, description: '', name: 'skip_param'
        string defaultValue: 'master', description: '', name: 'branch', trim: true
    }

    stages {
        stage ('Info'){
            steps {
                sh 'echo "skip_param= ${skip_param}"'
                sh 'echo "branch= ${branch}"'
            }

        }
        stage ('Checkout'){
            steps {
                git branch: "${branch}", url: "https://github.com/jenkinsci/jenkins.git"
            }
        }

        stage ('Build'){
            steps {
                sh "mvn clean package -DskipTests=${skip_param}"
            }
        }
    }   
}