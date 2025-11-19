def call(String imageName,String tag, String dockerHubUser) {
  withCredentials([usernamePassword(
            credentialsId: "dockerHub",
            usernameVariable: "dockerHubUser",
            passwordVariable: "dockerHubPass"
        )]) {
            // Secure login
            sh "echo ${dockerHubPass} | docker login -u ${dockerHubUser} --password-stdin"

            // Tag the image
            sh "docker image tag ${imageName}:${tag} ${dockerHubUser}/${imageName}:${tag}"

            // Push to DockerHub
            sh "docker push ${dockerHubUser}/${imageName}:${tag}"
        }
    }
