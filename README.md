# Reproducible example for [firebase-android-sdk#4831](https://github.com/firebase/firebase-android-sdk/issues/4831)

> ANR on app launch at com.google.firebase.perf.config.ConfigResolver.getInstance

The bug is also known at Taboola, [see archived thread in their support forums](https://web.archive.org/web/20240418075027/https://developers.taboola.com/taboolasdk/discuss/6513fe7b5b7e16002aaf8a44).

## What happens

This is an app that renders a webview. When you open the app once, everything looks alright.

When you open the app a second time, it will freeze upon starting and crash at some point.

From this point on you can never open the app again.

## Expected behavior

- You can open the app more than once

## Actual behavior

- When you open the app for a second time you get a freeze and an eventual crash. From this point on you can never open the app again.

## Additional information

By inpsecting the Android tombstone, you will find a potential deadlock that in the main thread caused by the Firebase Performance SDK.

We can reproduce this be using the Taboola SDK with a WebView that gets rendered at startup (which cused this issue for us).

The original issue suggests that this is caused by network requests happending before Firebase gets initialized.

Taboola knows about this issue and suggests changing the Android init order as a workaround (see [linked support thread](https://web.archive.org/web/20240418075027/https://developers.taboola.com/taboolasdk/discuss/6513fe7b5b7e16002aaf8a44)), which points in the same direction.

## Steps to reproduce

To reproduce the bug we use

- Expo (react-native)
- Firebase Performance SDK
- Taboola Android SDK
- A WebView
- TCF Consent (probably to permit specific network requests)

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
1. Close the app
1. Open the app again
