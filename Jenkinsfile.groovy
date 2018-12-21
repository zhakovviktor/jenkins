pipeline {
    agent any

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
                sh 'echo "skip_param = ${skip_param}"'
                sh 'echo "branch = ${branch}"'
            }

        }
        stage ('Build'){
            steps {
                git branch: "${branch}", url: "https://github.com/jenkinsci/jenkins.git"
            }
        }
    }   
}