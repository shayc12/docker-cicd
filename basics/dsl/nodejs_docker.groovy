job('NodeJS Docker example') {
    scm {
        git('https://github.com/shayc12/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL Jenkins')
            node / gitConfigEmail('jenkins-dsl@example.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('shayc12/jenkdocker')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('shaydocker')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

