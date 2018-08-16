pipeline {
    agent any
    parameters {
        choice(
                name: 'COMMIT_TYPE',
                choices: "branch\ntag\ncommit",
                description: 'commit类型，如果是branch name请选择branch type，如果是commit请选择commit type，如果是tag请选择tag type，选错跑的结果可能不对'
                )
        string(
                name: 'COMMIT_NAME',
                description: 'commit名称：branch name、commit id、tag name'
                )
        choice(
                name: 'TEST_TYPE',
                choices: "compile\nut\nall",
                description: '编译和UT两个功能供选择\nall执行两个功能'
                )
    }
    stages {
        stage('Notify'){
            steps {
                sh "bash dingding.sh \"${currentBuild.fullDisplayName} 开始构建\" " +
                    "${env.BUILD_URL} 0"
            }
        }
        stage('test') {
            steps {
                dir("/home/wiyr/Documents/src/test") {
                    sh "bash test.sh"
                }
            }
        }
    }
    post {
        success {
            sh "bash dingding.sh \"${currentBuild.fullDisplayName} 构建成功\" " +
                "${env.BUILD_URL} 1"
        }
        failure {
            sh "bash dingding.sh \"${currentBuild.fullDisplayName} 构建失败\" " +
                "${env.BUILD_URL} 2"
        }
    }
}
