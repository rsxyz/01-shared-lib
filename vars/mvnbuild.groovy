import com.myjenkins.MavenPipeline

def call(body) {
    def config= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def pipeline = new MavenPipelinea()
    pipeline.startPipeline(config)
}
