import ExpoTaboolaModule from "./ExpoTaboolaModule";
import ExpoTaboolaView from "./ExpoTaboolaView";
import { ChangeEventPayload, ExpoTaboolaViewProps } from "./ExpoTaboola.types";

export function initTaboola(): void {
  return ExpoTaboolaModule.initTaboola();
}

export { ExpoTaboolaView, ExpoTaboolaViewProps, ChangeEventPayload };
