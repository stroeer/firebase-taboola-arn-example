import { useEffect } from "react";
import { Text, StyleSheet, View } from "react-native";
import perf from "@react-native-firebase/perf";

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

  return (
    <View style={styles.container}>
      <Text>Firebase Taboola ARN Example</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
});
