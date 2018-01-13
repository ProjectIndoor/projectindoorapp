# Project Indoor (Mobile)
 With this app installed in the mobile device a user can see the floor maps of HFT building and gets to know their location, track their recently walked path and gets to know their surrounding area like steps, cafeteria.

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development purposes. 
See [deployment](#deployment) for notes on how to deploy the project on an Android device.


## Prerequisites

To get started with development you first need to ensure a current Java JDK is installed on your System (JDK8 or later). You can get version 8 of the Java Development Kit from Oracle [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or from the repository of your distribution when using Linux.

Further, you need to have the current Android SDK installed. 
However, the use of the IDE Android Studio is recommended, which contains the recent Android SDK and supports SDK management, therefore an installation guide for the Android SDK is not part of these setup guidelines.

# Installing

To get a basic setup you just need to clone the project and run a few commands to get started:

```sh
# Clone the project
git clone https://github.com/ProjectIndoor/projectindoorapp.git

# Installs the Debug build (Mac or Linux)
./gradlew installDebug

# Installs the Debug build (Windows)
gradlew installDebug
```

Or just build and run the app from Android Studio (recommended), after cloning it.

This is the basic state you need to continue development. 
Although, you can develop and run the app using a text editor and the command line prompt, it is recommended to use Android Studio in order to run the application on a device. 


# Deployment

For deployment an APK file is provided. 
It can be accessed in [Releases](https://github.com/ProjectIndoor/projectindoorapp/releases). 
The latest release can be accessed [here](https://github.com/ProjectIndoor/projectindoorapp/releases/latest).

The attached APK file can be moved to an Android device's file system and then be installed by executing it.

## Built With

* [Android](https://developer.android.com/index.html) - Android framework used
* [Android Studio](https://developer.android.com/studio/index.html) - Android IDE used
* [Gradle](https://gradle.org/) - Build tool used

## Authors

This application was initially developed at the HFT Stuttgart for a software project by the students of the Software Technology Master in winter semester 17/18.

See also the list of [contributors](https://github.com//ProjectIndoor/projectindoorapp/contributors) who participated in this project.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details