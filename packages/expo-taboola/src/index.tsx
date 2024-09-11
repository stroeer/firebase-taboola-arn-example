import React from "react";
import { requireNativeModule } from "expo-modules-core";
import { requireNativeViewManager } from "expo-modules-core";

import type { FC } from "react";
import type { ViewProps } from "react-native";

interface WebViewPops extends ViewProps {
  name?: string;
}

const ExpoTaboolaModule = requireNativeModule("ExpoTaboola");
const NativeView = requireNativeViewManager<WebViewPops>("ExpoTaboola");

export const TaboolaWebView: FC<WebViewPops> = props => {
  return <NativeView {...props} />;
};

export const initTaboola = (): void => {
  ExpoTaboolaModule.initTaboola();
};
