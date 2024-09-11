import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoTaboola.web.ts
// and on native platforms to ExpoTaboola.ts
import ExpoTaboolaModule from './ExpoTaboolaModule';
import ExpoTaboolaView from './ExpoTaboolaView';
import { ChangeEventPayload, ExpoTaboolaViewProps } from './ExpoTaboola.types';

// Get the native constant value.
export const PI = ExpoTaboolaModule.PI;

export function hello(): string {
  return ExpoTaboolaModule.hello();
}

export async function setValueAsync(value: string) {
  return await ExpoTaboolaModule.setValueAsync(value);
}

const emitter = new EventEmitter(ExpoTaboolaModule ?? NativeModulesProxy.ExpoTaboola);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoTaboolaView, ExpoTaboolaViewProps, ChangeEventPayload };
