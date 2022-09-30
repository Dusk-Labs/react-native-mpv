import React, { useRef } from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
  requireNativeComponent,
} from 'react-native';

const App = () => {
  const videoRef = useRef();

  return (
    <View style={styles.root} >
      <Text style={styles.text}>Hello world</Text>
      <BasicSurfaceViewManager style={{flex: 1}} ref={videoRef} />
    </View>
  );
};

const styles = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: 'black',
  },
  text: {
    position: "absolute",
    zIndex: 2,
    color: "white",
    fontSize: 24,
  },
  base: {
    overflow: "hidden",
    flex: 1,
    height: "100%",
    widhth: "100%",
  }
});

export default App;

const BasicSurfaceViewManager = requireNativeComponent('RCTMpvView');
