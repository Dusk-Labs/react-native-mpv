import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';
import BasicSurfaceViewManager from './components/BasicView';



const App = () => {
  return (
<View style={{justifyContent:"center",alignItems:"center",flex:1}}>
  <BasicSurfaceViewManager />
</View>        
  );
};

const styles = StyleSheet.create({
 
});

export default App;
