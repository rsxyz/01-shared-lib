def call(body) {
    def config= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
   node {
        git credentialsId: 'github', url: 'https://github.com/rsxyz/HelloJava.git'
        sh "mvn clean install"
    }
}
