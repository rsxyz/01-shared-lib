def call(body) {
    def config= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
   node {
        stage('checkout'){
           git credentialsId: 'github', url: config.url
        }
        stage('build'){
           sh "mvn clean install"
        }
    }
}
