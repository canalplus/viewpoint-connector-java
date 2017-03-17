#!groovy

/*
Issue with non serializable local variables :
https://github.com/jenkinsci/pipeline-plugin/blob/master/TUTORIAL.md#serializing-local-variables

Since pipelines must survive Jenkins restarts, the state of the running program is periodically saved to disk so it can
be resumed later (saves occur after every step or in the middle of steps such as sh).

The “state” includes the whole control flow including: local variables, positions in loops, and so on. As such: any
variable values used in your program should be numbers, strings, or other serializable types, not “live” objects such
as network connections.

If you must use a nonserializable value temporarily: discard it before doing anything else. When you keep it only as a
local variable inside a function, it is automatically discarded as soon as the function returned.

However the safest approach is to isolate use of nonserializable state inside a method marked with the annotation
@NonCPS. Such a method will be treated as “native” by the Pipeline engine, and its local variables never saved. However
it may not make any calls to Pipeline steps.
 */

import hudson.AbortException;

failed = false

try {
    if (testStep.toBoolean()) {
        stage 'Build'
        node {
            // Clone
            git branch: env.branch, credentialsId: env.credentials, url: env.src
            sh "mvn clean deploy"
        }
    }
} catch(e) {
    stepFailed('Build')
}

try {
    if (sonarStep.toBoolean()) {
        stage 'Sonar'

        node {
            git branch: env.branch, credentialsId: env.credentials, url: env.src
            sh "mvn sonar:sonar"
        }
    }
} catch(e) {
    stepFailed('Sonar')
}

if(!failed) {
    notifySlack(env.job_name + ' finished successfully :+1:', '#36a64f')
}

def stepFailed (name) {
    failed = true
    notifySlack(env.job_name + ' failed (step: ' + name + ') :-1:', '#F7003E')
    throw new AbortException(name + ' failed');
}

def notifySlack(text, color) {
    if('PROD'.equals(env.env_name) || 'PREPROD'.equals(env.env_name)) {
        slackSend channel: 'editoci', color: color, message: text + ' <' + env.BUILD_URL + '|#' + env.BUILD_NUMBER +'> ', teamDomain: 'canal-plus', token: 'rxFXG1uN2c1t4h3myvUEsAEq'
    }
}

