import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ExpoTaboolaViewProps } from './ExpoTaboola.types';

const NativeView: React.ComponentType<ExpoTaboolaViewProps> =
  requireNativeViewManager('ExpoTaboola');

export default function ExpoTaboolaView(props: ExpoTaboolaViewProps) {
  return <NativeView {...props} />;
}
