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

## Navigation
Current implementation is with Nav3. Check old implementation with nav2 in this branch: [nav2](https://github.com/santimattius/kmp-entertainment-app/tree/nav2)

## Dependencies
```toml
[versions]
androidGradlePlugin = "8.13.0"
kotlinVersion = "2.2.21"
ksp = "2.3.0"
composeVersion = "1.10.0-beta01"
buildkonfigGradlePlugin = "0.17.1"

androidx-room = "2.8.3"
sqlite = "2.6.1"

activity-compose = "1.11.0"
ui-tooling = "1.9.4"
appcompat = "1.7.1"

material3="1.9.0-beta03"
material="1.7.3"

coil= "3.3.0"
core-ktx = "1.17.0"

kotlinxCoroutinesAndroid = "1.10.2"

ktorVersion = "3.3.2"

koin = "4.1.1"

androidx-navigation = "2.9.1"
androidx-lifecycle = "2.10.0-alpha04"
androidx-nav3 = "1.0.0-alpha04"
androidx-adaptive = "1.2.0"

kermit = "2.0.8"
```

Check voyager implementation [here](https://github.com/santimattius/kmp-entertainment-app/tree/feature/voyager)
