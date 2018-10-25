def deploymentConfig = null
node {
    deploymentConfig = readJSON text: params.deploymentConfig
    echo "\u27A1 Deployment Config : "
    echo "${groovy.json.JsonOutput.prettyPrint(deploymentConfig.toString())}" 
	dir(deploymentConfig.checkoutDir) {
                echo 'Building.. ...' 
                bat 'mvn clean install' 
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
	} 
}