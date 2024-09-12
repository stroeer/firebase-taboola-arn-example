import React from "react";
import { requireNativeModule } from "expo-modules-core";
import { requireNativeViewManager } from "expo-modules-core";

import type { FC } from "react";
import type { ViewProps } from "react-native";

interface WebViewPops extends ViewProps {
  name?: string;
}

// todo missing IABTCF_PublisherRestrictions{ID} : `String ['0','1', or '2']`
export interface TcPreferences {
  IABTCF_CmpSdkID?: number;
  IABTCF_CmpSdkVersion?: number;
  IABTCF_PolicyVersion?: number;
  IABTCF_gdprApplies?: number;
  IABTCF_PublisherCC?: string;
  IABTCF_PurposeOneTreatment?: number;
  IABTCF_UseNonStandardTexts?: number;
  IABTCF_TCString?: string;
  IABTCF_VendorConsents?: string;
  IABTCF_VendorLegitimateInterests?: string;
  IABTCF_PurposeConsents?: string;
  IABTCF_PurposeLegitimateInterests?: string;
  IABTCF_SpecialFeaturesOptIns?: string;
  IABTCF_PublisherConsent?: string;
  IABTCF_PublisherLegitimateInterests?: string;
  IABTCF_PublisherCustomPurposesConsents?: string;
  IABTCF_PublisherCustomPurposesLegitimateInterests?: string;
}

const ExpoTaboolaModule = requireNativeModule("ExpoTaboola");
const NativeView = requireNativeViewManager<WebViewPops>("ExpoTaboola");

export const TaboolaWebView: FC<WebViewPops> = (props) => {
  return <NativeView {...props} />;
};

export const initTaboola = (): void => {
  ExpoTaboolaModule.initTaboola();
};

export const giveTcfConsent = (): void => {
  ExpoTaboolaModule.giveTcfConsent();
};
