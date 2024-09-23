# Reproducible example for [firebase-android-sdk#4831](https://github.com/firebase/firebase-android-sdk/issues/4831)

> ANR on app launch at com.google.firebase.perf.config.ConfigResolver.getInstance

The bug is also known at Taboola, [see archived thread in their support forums](https://web.archive.org/web/20240418075027/https://developers.taboola.com/taboolasdk/discuss/6513fe7b5b7e16002aaf8a44).

## What happens

This is an app that renders a webview. When you open the app once, everything looks alright.

When you open the app a second time, it will freeze upon starting and crash at some point.

From this point on you can never open the app again.

**Expected behavior** You can open the app more than once

**Actual behavior** When you open the app for a second time you get a freeze and an eventual crash. This behaviour then persists.

## Additional information

We can reproduce this be using the Taboola SDK with a WebView that gets rendered at startup (which cused this issue for us).

If you take any of these components away

- Firebase Performance SDK
- Taboola
- The WebView
- TCF Consent

The app will work.

The original issue suggests that this is caused by network requests happending before Firebase gets initialized.

Taboola knows about this issue and suggests changing the Android init order as a workaround (see [linked support thread](https://web.archive.org/web/20240418075027/https://developers.taboola.com/taboolasdk/discuss/6513fe7b5b7e16002aaf8a44)), which points in the same direction.

> My personal interpretation here is that after the app has started once, Taboola has been established and will start a
> network request during the next start before Firebase is fully initialized and the app will run into a deadlock.

- Firebase is the issue
- Taboola will cause the network request but only via WebView and only with consent (TCF)

#### ANR File from a tombstone

By inpsecting the Android tombstone, you will find a potential deadlock that in the main thread caused by the Firebase Performance SDK.

This file from a Tombstone after a freeze / crash is included in this repo: [`anr_2024-09-12-15-22-21-145`](./anr_2024-09-12-15-22-21-145)

```
----- pid 15409 at 2024-09-12 15:22:21.145074474+0200 -----
Cmd line: com.lukasbombachstroeer.firebasetaboolaarnexample
Build fingerprint: 'google/sdk_gphone64_arm64/emu64a:15/AP31.240617.003/12088229:user/release-keys'
ABI: 'arm64'
Build type: optimized
Debug Store: 1,0,4107366::
suspend all histogram:	Sum: 33us 99% C.I. 0.080us-8us Avg: 2.538us Max: 8us
DALVIK THREADS (25):
"main" prio=5 tid=1 Blocked
  | group="main" sCount=1 ucsCount=0 flags=1 obj=0x71c6a9b8 self=0xb4000076de662380
  | sysTid=15409 nice=-10 cgrp=top-app sched=0/0 handle=0x78f5a76d20
  | state=S schedstat=( 252827325 15285081 198 ) utm=22 stm=2 core=1 HZ=100
  | stack=0x7ff2d22000-0x7ff2d24000 stackSize=8188KB
  | held mutexes=
  at com.google.firebase.perf.config.ConfigResolver.getInstance(unavailable:2)
  - waiting to lock <0x02d05d9f> (a java.lang.Class<com.google.firebase.perf.config.ConfigResolver>) held by thread 18
  at com.google.firebase.perf.FirebasePerfEarly.<init>(FirebasePerfEarly.java:41)
```

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
1. 💥
