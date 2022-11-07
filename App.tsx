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
import  BasicSurfaceView from './BasicSurfaceViewManager';

const App = () => {
  const videoRef = useRef();

  return (
    <View style={styles.root} >
      <Text style={styles.text}>Hello world</Text>
      <BasicSurfaceView ref={videoRef} style={{flex:1}} radius={10} test="test" />
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
    margin: 100,
  },
  base: {
    overflow: "hidden",
    flex: 1,
    height: "100%",
    widhth: "100%",
  }
});

export default App;

//const BasicSurfaceViewManager = requireNativeComponent('RCTMpvView');
