def call(body) {
    def config= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    pipeline{
        agent any
        tools {
          jdk 'JDK8'
          maven 'M2'
        }
        stages{
            stage('Checkout'){
               steps{
                   git credentialsId: 'github', url: 'https://github.com/rsxyz/HelloJava.git'
               }
            }
            stage('Build') {
                steps {
                    sh "mvn clean install"
                }
            }
            stage("Test: SonarQube") {
                steps {
                    echo "sonar ..."
                }
            }
            stage('Publish Artifactory') {
                steps {
                    echo "artifactory  ..."
                }
            }
        }
    }
}
