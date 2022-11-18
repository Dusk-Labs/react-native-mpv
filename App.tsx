import React, { useRef } from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';
import  BasicSurfaceView from './BasicSurfaceViewManager';

const App = () => {
  let src ="https://rr12---sn-uxax4vopj55gb-x1xl.googlevideo.com/videoplayback?expire=1668824799&ei=f-p3Y4rpDvaD-LAPh66hmAM&ip=2802%3A8010%3A494b%3A9c00%3A747e%3A4236%3Ac6d3%3A274&id=o-ABQhxG4_GN6L1EgKmoSGXP6T9CU5emjv_r6OUPpdHF6e&itag=397&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278%2C394%2C395%2C396%2C397%2C398%2C399&source=youtube&requiressl=yes&mh=N_&mm=31%2C29&mn=sn-uxax4vopj55gb-x1xl%2Csn-x1x7dnez&ms=au%2Crdu&mv=m&mvi=12&pl=42&initcwndbps=735000&vprv=1&mime=video%2Fmp4&ns=-hkcAgDd2ANFKemqN4s2eEoJ&gir=yes&clen=307387203&dur=5759.680&lmt=1611743528540273&mt=1668802876&fvip=3&keepalive=yes&fexp=24001373%2C24007246&c=WEB&txp=5531432&n=1thLYhWKeGgoQA&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRgIhAKBTIT2tNzSat9af75JZ3SRkPoX9ITCbynU__DTVDaqKAiEA2CI9RJGO2tmNUUtJCnQ06JXiHsC0ZzO49WGitM_Zza8%3D&alr=yes&sig=AOq0QJ8wRQIhAJcaFag40Rd8hN4tCFMSMJ4V9dbg-LIOEN3hyyb-OfO6AiAx261VgmkbCy-rF7dNDIhOxy-sXWOlnkw5-SuAJ7pTcQ%3D%3D&cpn=e_P8vrZ0kll7rKX4&cver=2.20221118.01.00";
  src="https://rr12---sn-uxax4vopj55gb-x1xl.googlevideo.com/videoplayback?expire=1668824799&ei=f-p3Y4rpDvaD-LAPh66hmAM&ip=2802%3A8010%3A494b%3A9c00%3A747e%3A4236%3Ac6d3%3A274&id=o-ABQhxG4_GN6L1EgKmoSGXP6T9CU5emjv_r6OUPpdHF6e&itag=251&source=youtube&requiressl=yes&mh=N_&mm=31%2C29&mn=sn-uxax4vopj55gb-x1xl%2Csn-x1x7dnez&ms=au%2Crdu&mv=m&mvi=12&pl=42&initcwndbps=735000&vprv=1&mime=audio%2Fwebm&ns=-hkcAgDd2ANFKemqN4s2eEoJ&gir=yes&clen=94872877&dur=5759.701&lmt=1611655153955377&mt=1668802876&fvip=3&keepalive=yes&fexp=24001373%2C24007246&c=WEB&txp=5431432&n=1thLYhWKeGgoQA&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRAIgKySYSRyNISa7ePQiScTZ74v7HUd9r1pXO-ohqEt9ST4CIGCeLQAZUR4oSeL14AyotWDwID4cNS-uBGl36APL0aUq&alr=yes&sig=AOq0QJ8wRQIhAJ8L8EwQtmdm4E8CGDCZnMxKUQ9LMp1X7iuP9lKDelEAAiANaoqPHxNkU2RRZSn_yQiWRhwYuvnzN1iW2pA_uAUeYQ%3D%3D&cpn=e_P8vrZ0kll7rKX4&cver=2.20221118.01.00";
  return (
    <View style={styles.root} >
      <StatusBar hidden={true}/>
      <Text style={styles.text}>Hello world</Text>
      <BasicSurfaceView  style={{flex:1}} pause={false} src={src}/>
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

