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
androidGradlePlugin = "8.7.1"
kotlinVersion = "2.0.20"
ksp = "2.0.20-1.0.25"
composeVersion = "1.7.0"
androidx-room = "2.7.0-alpha10"
sqlite = "2.5.0-SNAPSHOT"

activity-compose = "1.9.3"
appcompat = "1.7.0"
buildkonfigGradlePlugin = "0.15.1"
coil= "3.0.0-alpha08"
core-ktx = "1.13.1"
kotlinxCoroutinesAndroid = "1.9.0"
ktorVersion = "2.3.12"
koin = "4.0.0"

androidx-navigation = "2.8.0-alpha08"
androidx-lifecycle = "2.8.2"
startupRuntime = "1.2.0"
```

Check voyager implementation [here](https://github.com/santimattius/kmp-entertainment-app/tree/feature/voyager)
