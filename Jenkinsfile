pipeline {
    agent any
  //triggers {pollSCM('* * * * *')}
  stages {
    stage('Checkout') {
      steps {
        // Get some code from a GitHub repository
        git branch: "main", url: 'https://github.com/Alex-Lin5/PlanetsAndMoons.git'
      }
    }
        stage('Build') {
      steps {
       // sh 'chmod a+x mvn'
        sh 'mvn clean package -DskipTests=true'
      }

          post {
        always {
          archiveArtifacts 'target/*.jar'
        }
          }
        }
  }
}