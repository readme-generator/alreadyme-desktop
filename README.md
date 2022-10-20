<h1>ALREADYME.md Desktop Application</h1>

<p>

![kotlin-version](https://img.shields.io/badge/kotlin-1.6.10-A97BFF)
![ktor-client](https://img.shields.io/badge/ktor-client-5675DF)
![compose-multiplatform](https://img.shields.io/badge/compose-multiplatform-32A579)
![license](https://img.shields.io/badge/License-Apache2.0-brightgreen)
![FOSSA Status](https://app.fossa.com/api/projects/custom%2B33996%2Fgithub.com%2Freadme-generator%2Falreadyme-desktop.svg?type=shield)
[![CodeFactor](https://www.codefactor.io/repository/github/readme-generator/alreadyme-desktop/badge)](https://www.codefactor.io/repository/github/readme-generator/alreadyme-desktop)

![bootstrap-final](https://user-images.githubusercontent.com/72238126/196931823-b15f32a4-a63b-4fab-a25b-27a2449e688e.gif)

**ALREADYME** is a multiplatform desktop application which create a `README.md` through only a URL of github repository. While generating `README.md`, it shows the creation process in real time using WebSockets. Also you can directly download generated `README.md` and pull request it to target repository.

</p>

<br>

# How to build

If you want to build this project on your local, you only need following:

- Gradle 7.3.3
- JDK >= 16

<br>

# Run execution

You can execute this project or create binary file through following commands:

### On Windows
```shell
./gradlew run
./gradlew createDistributable
./gradlew runDistributable
```

### On Linux or MacOS
```shell
gradle run
gradle createDistributable
gradle runDistributable
```

<br>

- `run` is used to run an app locally. You need to define a `mainClass` — an fq-name of a class, containing the main function. Note, that run starts a non-packaged JVM application with full runtime. This is faster and easier to debug, than creating a compact binary image with minified runtime. To run a final binary image, use `runDistributable` instead.
- `createDistributable` is used to create a prepackaged application image a final application image without creating an installer.
- `runDistributable` is used to run a prepackaged application image.

<br>

# Available formats

The following formats available for the supported operating systems:

- macOS — `.dmg`, `.pkg`
- Windows — `.exe`, `.msi`
- Linux — `.deb`, `.rpm`

<br>

If you need more help for native distributions and local execution, please check [this](https://github.com/JetBrains/compose-jb/tree/master/tutorials/Native_distributions_and_local_execution).

<br>

# Architectures

**ALREADYME** is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

![architecture](https://user-images.githubusercontent.com/72238126/196948033-79646596-1b48-4962-ac8d-14c88af4f974.png)

The overall architecture of **ALREADYME** is composed of two layers; the UI layer and the Data layer. Each layer has dedicated components and they have each different responsibilities, as defined below:

### Overview

![overview](https://github.com/skydoves/Pokedex/raw/main/figure/figure1.png)

- Each layer follows [unidirectional event/data flow](https://developer.android.com/topic/architecture/ui-layer#udf); the UI layer emits user events to the data layer, and the data layer exposes data as a stream to other layers.
- The data layer is designed to work independently from other layers and must be pure, which means it doesn't have any dependencies on the other layers.

With this loosely coupled architecture, you can increase the reusability of components and scalability of your app.

<br>

# Tech stack & Open-source libraries
- 100% [Kotlin](https://kotlinlang.org/) based.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Compose Multiplatform](https://github.com/JetBrains/compose-jb) for desktop application UI.
- [Ktor](https://github.com/ktorio/ktor) for HTTP & WebSockets client.
- [Dagger](https://github.com/google/dagger) for dependency injection.
- [Kotlin multiplatform markdown renderer](https://github.com/mikepenz/multiplatform-markdown-renderer)

<br>

# Supported OS
[![Windows](https://img.shields.io/badge/Windows-blue?&logo=windows)]()
[![Linux](https://img.shields.io/badge/Linux-orange?&logo=ubuntu&logoColor=white)]()
[![MacOS](https://img.shields.io/badge/MacOS-black?&logo=macos)]()

> There may be minor issues with OS other than Windows.

<br>

# Service repositories

- [ALREADYME.md Organization](https://github.com/readme-generator)
- [Desktop Client](https://github.com/readme-generator/alreadyme-desktop)
- [Backend Server](https://github.com/readme-generator/alreadyme-backend)
- [AI Server](https://github.com/readme-generator/alreadyme-ai-serving)
- [AI Research](https://github.com/readme-generator/alreadyme-ai-research)


<br>

# License
```
Designed and developed by 2022 YJYOON (Yeojun Yoon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
