# Android application

This directory contains the Android Studio project for the FirePassword application. The app is structured around a Jetpack Compose front end, Kotlin/Java application logic, and a native layer that is being expanded through the Android NDK.

## What lives here

- app/: The main application module.
- app/src/main/java/: Kotlin and Java source files for the UI, navigation, view models, repository logic, and app entry points.
- app/src/main/cpp/: Native C/C++ sources, JNI bridge code, and the CMake build definition used by the Android build system.
- app/src/main/AndroidManifest.xml: Android manifest and application-level configuration.
- build.gradle.kts and settings.gradle.kts: Gradle configuration for the project.

## How to work on this project

### 1. Adding UI and app features

- Place new screens under app/src/main/java/<package>/ui/.
- Keep composables, view models, and navigation code organized by feature.
- Prefer small, focused files so the app stays easy to navigate as the native integration grows.

### 2. Adding or changing native code

- Put new C/C++ source files under app/src/main/cpp/.
- Update app/src/main/cpp/CMakeLists.txt if you add new source files or new include directories.
- If you expose new native functionality to Kotlin, add the corresponding JNI bridge entry point in the app’s Java/Kotlin layer and implement it in native-lib.cpp or a dedicated native source file.

### 3. Building and running

From this directory, use:

- ./gradlew assembleDebug for a debug build
- ./gradlew installDebug to deploy to a connected device or emulator

### 4. Keeping the structure maintainable

- Keep Kotlin UI logic separate from native integration details.
- Avoid scattering JNI bridge code throughout the app; centralize it in the bridge layer and keep the rest of the app focused on higher-level behavior.
- Document any change that affects the CMake build, NDK configuration, or JNI signatures.

## Current priority

The main engineering goal for this module is to continue migrating the project toward a robust NDK-based implementation and to keep the native C++ sources organized and buildable inside the Android app.
