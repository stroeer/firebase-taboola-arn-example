## Steps to reproduce

### Build the app

```sh
# Install dependencies
yarn

# Create Andoid project at `example-app/android`
yarn prebuild:android

# Build the android object as an debuggable apk
# The apk will be written to
# `example-app/android/app/build/outputs/apk/debug/app-debug.apk`
yarn build:android
```

### Start the app

1. Open an Android Emulator
1. Install the apk. For convenience, you can use `yarn install:android`
1. Start the react-native dev server (Metro) `yarn dev`
1. Open the app on the emulator
