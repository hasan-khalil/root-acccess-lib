# FirePassword

FirePassword is a research-oriented Android project that combines an Android application with native exploit-related source code. The current focus is the migration of the app’s native logic into the Android NDK and the continued expansion of the C++ implementation under the app’s native layer.

## Project overview

This repository is organized around three main areas:

- Android application code in the android directory, including the Kotlin/Compose UI, Java/Kotlin integration layer, and the native C/C++ sources built through CMake.
- Vulnerability research and exploit source material in the CVE-2026-43499 directory, including target-specific headers and proof-of-concept code.
- Helper web-based exploit demonstration assets in the exploit directory for local testing and debugging.

## Current development focus

The project is no longer centered on the earlier experimental login and account-creation flow. The priority now is to keep the Android app maintainable while expanding its NDK integration and native codebase.

## Repository structure

- android/: Android Studio project for the app, including Gradle configuration, the main application module, and native source files.
- CVE-2026-43499/: Research material for CVE-2026-43499, including exploit source files and target definitions.
- exploit/: Browser-based helper assets and demo scripts used for inspection and local experimentation.

## Working guidelines

- Keep the Android app code organized under the app module’s Kotlin and native source directories.
- When adding new native logic, place the implementation in the C/C++ source tree under android/app/src/main/cpp and update the CMake build configuration accordingly.
- Keep research and proof-of-concept code separate from the Android app’s production-facing code so the project remains easier to maintain.
- Document changes clearly as the project moves toward deeper NDK integration.
