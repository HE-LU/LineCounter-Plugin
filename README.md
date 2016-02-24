LineCounter - Plugin for IntelliJ based IDE
============
This plugin was created as a small internal project at STRV. Plugin will display list of files and methods which have bigger number of lines than user defined value.


Functionality
=============
1. After opening ToolWindows at the right panel, user has to fill maximum number of lines for methods and files that serve as threshold for filtering project files and methods. Based on those two values will be displayed tree with files / methods names and lines count.


Prerequisites
=============
You have to have installed IntelliJ IDEA IDE version 9.0 or later, and Java JDK, preferably version 7 or later. That should be all you need to start developing your own plugin, and running this example.

You can download IntelliJ IDEA Community edition here: [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)


Building project
================
You don't need to install Gradle on your system, because there is a [Gradle Wrapper](http://www.gradle.org/docs/current/userguide/gradle_wrapper.html). The wrapper is a batch script on Windows, and a shell script for other operating systems. When you start a Gradle build via the wrapper, Gradle will be automatically downloaded and used to run the build.

1. Clone this repository
2. Run `gradlew buildPlugin` in console
3. ZIP file should be available in project root directory

You can also test plugin implementation by launching new instance of IntelliJ IDEA with plugin installed, using `gradlew runPlugin`.


Developed by
============
* Lukáš Hermann ([hermann@helu.cz](hermann@helu.cz))
* STRV ([http://www.strv.com](http://www.strv.com))
