# AndroidTestPlugin

this is an gradle plugin for android test.

# Usage

## step1

modify the LOCAL_REPO_URL value in the gradle.properties

## step2

```
./gradlew uploadArchives

```

run the uploadArchives gradle task or just use the command to build the local repository.

## step3

install the test apk on your device, then your will be able to use gradle script to control android test.

please see [](https://github.com/heavy-james/AndroidTestApk) for more information.

# More

this plugin will be upload to the bintray server, and it will be much easier to use the test tools then.