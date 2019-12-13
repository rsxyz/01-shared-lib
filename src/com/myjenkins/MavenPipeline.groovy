package com.myjenkins

def startPipeline(config){
   config['gitBranch'] = env.BRANCH_NAME

   node {
        stage('checkout'){
           git credentialsId: config.GIT_TOKEN, url: config.GIT_URL
        }
        stage('build'){
           sh "mvn clean install"
        }
        stage('sonar'){
          sh "mvn sonar:sonar  -Dsonar.host.url=config.SONAR_URL"
       }
   }
}
