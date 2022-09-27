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
<View style={{justifyContent:"center",alignItems:"center",backgroundColor:"black",flex:1}}>
  <Text style={{color:"white"}}>Test</Text>
  <BasicSurfaceViewManager/>
</View>        
  );
};

const styles = StyleSheet.create({
 
});

export default App;
