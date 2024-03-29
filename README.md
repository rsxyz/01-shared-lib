# 01-shared-lib
* vars: This directory holds all the global shared library code that can be called from a pipeline. It has all the library files with a .groovy extension. It also supports .txt files for the documentation of shared library code. For example, if you have a file named maven-build.groovy, you can have a help file named maven-groovy.txt. In this file, you can write the respective shared library function help documentation in markdown format.  The help file can be viewed from <your-jenkins-url>/pipeline-syntax/globals page. 

* src: It is a regular java source directory. It is added to the classpath during every script compilation. Here you can add custom groovy code to extend yous shared library code. Also, you can import existing Jenkins and plugins classes using an import statement.

There will be scenarios where your groovy DSL’s will not be flexible enough to achieve some functionality. In this case, you can write custom groovy functions in src and call it in your shared library code.

* resources: All the non-groovy files required for your pipelines e.g common JSON template to make an API call during the build. This JSON template can be stored in the resources folder and called can be called in the shared library using libraryResource function.

* example:1
```bash
@Library('jenkins-shared-lib@master') _

pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
            gitCheckout(
                branch: "master",
                url: "https://github.com/rsxyz/HelloJava.git"
            )
            }
        }
    }
}

```
* example:2
```bash
@Library('jenkins-shared-lib@master') _
mvnbuild{
    GIT_TOKEN = 'github'
    GIT_URL= 'https://github.com/rsxyz/HelloJava.git'
    SONAR_URL = 'http://192.168.1.15:9000'
}
```
