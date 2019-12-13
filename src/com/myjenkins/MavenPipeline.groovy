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
          sh "mvn sonar:sonar  -Dsonar.host.url=${config.SONAR_URL}"
       }
        stage('Artifactory Deploy') {
            def server = Artifactory.server "artifactory"
            def buildInfo = Artifactory.newBuildInfo()
            def rtMaven = Artifactory.newMavenBuild()
            rtMaven.tool = 'M2'
            rtMaven.deployer releaseRepo:'maven-dev-local', snapshotRepo:'maven-dev-local', server: server
            rtMaven.resolver releaseRepo:'maven-dev-local', snapshotRepo:'maven-dev-local', server: server
            rtMaven.run pom: 'pom.xml', goals: 'clean install -Dmaven.test.skip=true', buildInfo: buildInfo
            publishBuildInfo server: server, buildInfo: buildInfo
        }
   }
}
