node {
   def gradleHome
   stage('Preparation') { // for display purposes
      // Get code from GitHub repository
      git 'https://github.com/ProjectIndoor/projectindoorweb'
      // get Gradle
      gradleHome = tool name: 'DefaultGradle', type: 'gradle'
   }
   stage('Build') {
      // Run the gradle build
      if (isUnix()) {
         sh "./gradlew clean build --info"
      } else {
         bat "./gradlew.bat clean build --info"
      }
   }
}
