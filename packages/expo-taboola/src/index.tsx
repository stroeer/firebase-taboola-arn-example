import React from "react";
import { requireNativeModule } from "expo-modules-core";
import { requireNativeViewManager } from "expo-modules-core";

import type { FC } from "react";

interface ViewPops {
  name: string;
}

const ExpoTaboolaModule = requireNativeModule("ExpoTaboola");
const NativeView = requireNativeViewManager<ViewPops>("ExpoTaboola");

export const TaboolaWebView: FC<ViewPops> = props => {
  return <NativeView {...props} />;
};

export const initTaboola = (): void => {
  ExpoTaboolaModule.initTaboola();
};
