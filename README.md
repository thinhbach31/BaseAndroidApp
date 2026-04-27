# BaseAndroidApp

A production-ready Android starter template built with **Kotlin** and **Jetpack Compose**. Clone this repo as the foundation for any new Android project — all the boilerplate is already handled so you can focus on building features from day one.

---

## Why use this as your starting point?

Starting a new Android project from scratch means repeating the same setup every time: configuring Gradle, picking library versions, wiring up Compose, and deciding on a project structure. This template makes all of those decisions upfront with modern, well-maintained defaults so you never have to start from zero again.

---

## What's included

- **Jetpack Compose** with Material3 — UI toolkit and design system ready to go
- **Version catalog** (`gradle/libs.versions.toml`) — all dependencies declared in one place, easy to update
- **Kotlin 2.2.10** with full Compose compiler support
- **Android Gradle Plugin 9.2.0** — latest stable build tooling
- **Compose BOM 2026.02.01** — all Compose library versions kept in sync automatically
- **Min SDK 24 / Target SDK 36** — broad device coverage with access to the latest APIs
- **ProGuard config** — release build shrinking pre-configured
- **Test scaffolding** — JUnit 4, Espresso, and Compose UI test dependencies included out of the box

---

## How to start a new project from this base

1. **Clone the repo**
   ```bash
   git clone git@github.com:thinhbach31/BaseAndroidApp.git MyNewApp
   cd MyNewApp
   ```

2. **Rename the app**
   - Update `rootProject.name` in `settings.gradle.kts`
   - Update `namespace` and `applicationId` in `app/build.gradle.kts`
   - Rename the package directory under `app/src/` to match your new package name

3. **Set your version info**
   - Update `versionCode` and `versionName` in `app/build.gradle.kts`

4. **Reset git history** (optional but recommended)
   ```bash
   rm -rf .git
   git init
   git add .
   git commit -m "Initial commit from BaseAndroidApp template"
   ```

5. **Start building** — add your features, screens, and dependencies on top of the clean base.

---

## Project structure

```
BaseAndroidApp/
├── app/
│   ├── src/
│   │   ├── main/        # App source code
│   │   ├── test/        # Unit tests
│   │   └── androidTest/ # Instrumented tests
│   └── build.gradle.kts
├── gradle/
│   ├── libs.versions.toml   # Version catalog
│   └── wrapper/
├── build.gradle.kts
└── settings.gradle.kts
```

---

## Tech stack

| Layer | Library |
|---|---|
| Language | Kotlin 2.2.10 |
| UI | Jetpack Compose + Material3 |
| Build | Android Gradle Plugin 9.2.0 |
| Dependency management | Gradle Version Catalog |
| Testing | JUnit 4, Espresso, Compose UI Test |

---

## Requirements

- Android Studio Meerkat or newer
- JDK 11
- Android SDK with API 36 installed
