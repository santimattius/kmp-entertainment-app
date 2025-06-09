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
androidGradlePlugin = "8.9.3"
kotlinVersion = "2.1.21"
ksp = "2.1.21-2.0.1"
composeVersion = "1.8.1"
androidx-room = "2.7.1"
sqlite = "2.5.1"

activity-compose = "1.10.1"
ui-tooling = "1.8.2"
appcompat = "1.7.1"
buildkonfigGradlePlugin = "0.17.0"
coil= "3.2.0"
core-ktx = "1.16.0"
kotlinxCoroutinesAndroid = "1.10.2"
ktorVersion = "3.1.3"
koin = "4.1.0"

androidx-navigation = "2.9.0-beta01"
androidx-lifecycle = "2.9.0"
kermit = "2.0.4"
```

Check voyager implementation [here](https://github.com/santimattius/kmp-entertainment-app/tree/feature/voyager)
