import { useEffect } from "react";
import { Text, StyleSheet, View } from "react-native";
import perf from "@react-native-firebase/perf";
import { initTaboola, giveTcfConsent } from "expo-taboola";

/**
 * Giving TCF consent is necessary for reproducing the issue
 * otherwise the ARN will not happen
 */
giveTcfConsent();

/**
 * We also need to initialize Taboola SDK
 * todo: really? maybe not needed
 */
initTaboola();

/**
 * We do some API calls here to proof that the Performance SDK
 * is properly integrated and working.
 */
function useCustomTraceToShowPerfSdkIsProperlyIntegrated() {
  useEffect(() => {
    perf()
      .startTrace("custom_trace_for_proof")
      .then((trace) => {
        trace.putAttribute("perf_works", "true");
        return trace;
      })
      .then((trace) => trace.stop())
      .then(() => console.log("Custom trace created successfully"))
      .catch((error) => {
        console.error("Failed to create custom trace");
        throw error;
      });
  });
}

export default function HomeScreen() {
  useCustomTraceToShowPerfSdkIsProperlyIntegrated();

  return <View style={styles.container}>Firebase Performance SDK ARN example</View>;
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
});
