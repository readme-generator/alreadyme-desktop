# alreadyme-desktop

> Here is the desktop environment for Android application which contains alreadyme.tv.

## Build example

##### 1. Install dependencies

##### 2. Add source folder to `dist` directory

```
android tools develop --data-dir=src/alreadyme
```

##### 3. Build

######### 4. In debug mode, run `build.sh`

##### 5. As debug build

```
mvn clean install
```

##### 6. Release build

##### 7. In release mode

##### (notes)

1. The build directory will be copied to `lib` folder.

##### 2. Run `build.sh`

##### 3. In debug mode, run `build.sh`

##### 4. In release mode, run `build.sh`

##### 5. Build

##### 6. Release

## Run example

##### 1. Open terminal. open `dist` folder

##### 2. Run `build.sh`

##### 3. Check console

##### 4. Application is running as an Android thing.

##### 5. Devices

##### 6. Apps

## Installation

### By the dev version

##### 1. Clone the repository

##### 2. Copy `src.kv` file into `dist` folder

##### 3. Copy `main.kt` file into `dist` folder

##### 4. Copy `main.s.kt` file into `dist` folder

##### 5. Sign into github

##### 6. Run `mvn package`

##### 7. The package adapter is:

##### 1-a. ``addPackage``

##### 1-b. ``addDependency``

##### 1-c. `_addPackageManifest`

##### 1-d. `_addDependencies`

##### 2. Add an instance of `BootProgressListener` in this application's main class

##### 3. Add `Xamarin.Build.`

##### 4. Apply ``mvn build`` listener

##### 5. Run `build.sh` every time the `ফিচার` or `API` tool is started or stopped

##### 6. To run the copy watch `copy watch` command

##### 7. To reduce memory usage, do not run build.sh every time

### By the release version

##### 1. Open terminal

##### 2. Find and run `mvn package`

##### 3. The package adapter is:

##### 1-a. `_addPackageManifest`

##### 1-b. `_addDependencies`

##### 1-c. `_addPackageManifest`

##### 1-d. `_addXamarin.Build`

##### 2. Apply ``mvn build`` listener

##### 3. Run `build.sh`

##### 4. Generate `lib` and `libx` binaries

##### 5. Download to local system

##### 6. Create an `app.yml` with the name of `alreadyme`

##### 7. Run `mvn package`

##### 8. The package adapter is:

##### 1-a. `_addXamarin.Build`

##### 1-b. `_addPackageManifest`

##### 1-c. `_addDependencies`

##### 1-d. `_add