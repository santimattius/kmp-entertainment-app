# KMP Entertainment App

[//]: # (Screenshot)
<p align="center">
  <img width="270" src="https://github.com/santimattius/kmp-entertainment-app/blob/feature/precompose-navigation/screenshots/kmp-android.png?raw=true"/>
  <img width="320" src="https://github.com/santimattius/kmp-entertainment-app/blob/feature/precompose-navigation/screenshots/kmp-ios.png?raw=true"/>
</p>

## Content

TheMovieDB API: Check this [documentation](https://www.themoviedb.org/documentation/api).

## Setup

Using local properties for define api key:

```properties
apiKey="{your-api-key}"
```

## Set up the environment

> **Warning**
> You need a Mac with macOS to write and run iOS-specific code on simulated or real devices.
> This is an Apple requirement.

To work with this template, you need the following:

* A machine running a recent version of macOS
* [Xcode](https://apps.apple.com/us/app/xcode/id497799835)
* [Android Studio](https://developer.android.com/studio)
* The [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile)
* The [CocoaPods dependency manager](https://kotlinlang.org/docs/native-cocoapods.html)

### Check your environment

Before you start, use the [KDoctor](https://github.com/Kotlin/kdoctor) tool to ensure that your development environment is configured correctly:

1. Install KDoctor with [Homebrew](https://brew.sh/):

    ```text
    brew install kdoctor
    ```

2. Run KDoctor in your terminal:

    ```text
    kdoctor
    ```

   If everything is set up correctly, you'll see valid output:

   ```text
   Environment diagnose (to see all details, use -v option):
   [✓] Operation System
   [✓] Java
   [✓] Android Studio
   [✓] Xcode
   [✓] Cocoapods
   
   Conclusion:
     ✓ Your system is ready for Kotlin Multiplatform Mobile development!
   ```

Otherwise, KDoctor will highlight which parts of your setup still need to be configured and will suggest a way to fix them.

## Dependencies
```toml
[versions]
androidGradlePlugin = "8.2.2"
kotlinVersion = "1.9.23"
ksp = "1.9.23-1.0.20"
composeVersion = "1.6.10-rc01"
androidx-room = "2.7.0-alpha02"
sqlite = "2.5.0-SNAPSHOT"

activity-compose = "1.9.0"
appcompat = "1.6.1"
buildkonfigGradlePlugin = "0.15.1"
coil= "3.0.0-alpha06"
core-ktx = "1.13.1"
kotlinxCoroutinesAndroid = "1.8.0"
ktorVersion = "2.3.10"
koin = "3.6.0-alpha3"
koinComposeMultiplatform = "1.2.0-Beta4"

androidx-navigation = "2.8.0-alpha02"
androidx-lifecycle = "2.8.0-rc03"
```

Check voyager implementation [here](https://github.com/santimattius/kmp-entertainment-app/tree/feature/voyager)
