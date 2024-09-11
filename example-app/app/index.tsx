import { useEffect } from "react";
import { Text, StyleSheet, View } from "react-native";
import perf from "@react-native-firebase/perf";
import { initTaboola, TaboolaWebView } from "expo-taboola";

initTaboola();

function useCustomTraceToShowPerfSdkIsProperlyIntegrated() {
  useEffect(() => {
    perf()
      .startTrace("custom_trace_for_proof")
      .then(trace => {
        trace.putAttribute("perf_works", "true");
        return trace;
      })
      .then(trace => trace.stop())
      .then(() => console.log("Custom trace created successfully"))
      .catch(error => {
        console.error("Failed to create custom trace");
        throw error;
      });
  });
}

export default function HomeScreen() {
  useCustomTraceToShowPerfSdkIsProperlyIntegrated();

  return <TaboolaWebView name="taboolawebview" style={styles.container} />;
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
});
