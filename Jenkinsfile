/* Utility function to fetch from the folder properties the name of the cloud enviroment that student<nn>
 * is allowed to use. The value is "k8s-devops<nn>"
*/
def get_kubernetes_student_cloud() {
  def cloudName;
  withFolderProperties {
    cloudName = env.CLOUD_ENV
  }
  return cloudName
}

pipeline {
    // definition of the agent that will run the pipeline
    agent {
      kubernetes {
        cloud get_kubernetes_student_cloud()
        yamlFile 'agents/jenkins-kaniko-agent.yaml'
      }
    }
    stages {
        stage('Build Image') {
            steps {
                sh 'echo "Here go the steps to build the docker container"'
            }
        }
        stage('Tag Repository') {
            steps {
                sh 'echo "Tag the git repository if build was successful"'
            }
        }
    }
}