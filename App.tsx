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
 const src1="https://www.larmoire.info/jellyfish/media/jellyfish-40-mbps-hd-hevc-10bit.mkv"; 
 const src3="https://rr6---sn-uxax4vopj55gb-x1xl.googlevideo.com/videoplayback?expire=1669259626&ei=Co1-Y4iULs2C-LAPuZmFwAk&ip=2802%3A8010%3A4935%3A600%3A952e%3A693a%3A268a%3Aa805&id=o-ACkcQ_BJlnTzOoxHZEPvNMfxFGy8oEbFqdeVxtVEWojP&itag=251&source=youtube&requiressl=yes&mh=US&mm=31%2C29&mn=sn-uxax4vopj55gb-x1xl%2Csn-x1x7dn7s&ms=au%2Crdu&mv=m&mvi=6&pl=46&initcwndbps=832500&vprv=1&mime=audio%2Fwebm&ns=qzJBmfm_BdJx8WUay2oHN2IJ&gir=yes&clen=4341887&dur=375.841&lmt=1668673961257877&mt=1669237537&fvip=1&keepalive=yes&fexp=24001373%2C24007246&c=WEB&txp=5432434&n=-PhvvzI-Pw45Tg&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRAIgfAJ2L4NC5o6k6fJ_qjOzdcKzV-paEyR28dgcplXdrKQCICz_KzwWElCNQsY6YyYdreE2PfZCRDmtq-TF2NTRTOLe&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRAIgL6ZXDp7tiIYb_t6bIMAR9IugeqX8qMjlXfiizkq7sWcCIC_qZTCkgaDZyCYitP9ZoJKPVDNdUnb41nVKMl3vX0W7&alr=yes&cpn=Xjg2QA17FPkNCH_8&cver=2.20221122.06.00";
 const src5="https://00f74ba44b8745ba7f5546d87467eaacdbed165a89-apidata.googleusercontent.com/download/storage/v1/b/ralentando/o/chainsanwwwmannbesv2-01.mp4?jk=AFshE3VEFzXAw6u5dQl7njs4EAZMIlEOPaYRFTkrKyeMlygGyVOM-jaiq7IB36gb15Y4Wdb_rvqYi2N8qn4-seReijj0vZQ38dzLV0vqZJz9cgUToAlIlW1pjBlT4NW9YaAl7HrWDOYEFjVmjbOb_CeQ-RFqD2oUuIv6_oq_utxJwYSHEEcgkbDTQtRLAQSWamUahTe4_nsV-rBp-FJmPNDDJmtoRe9TE_bMFGGxvDcMWj-9V6g3EcaVds7-MWBZqrg7Cwa8JNyoIKnQB8Z7vdlzK0VD0FQslm8EeN0qTZrpvoM9fp1SvtBEfWKTzrL2nZlz2jcxzgpPTOKczA2AHcR9NiGzSYTvChRq_cbZOmSWqn8K4nbXBzDJ0hJMS-dwuX-ASMuHlteq0YutBXrkbSm_lO5tVngNn5WaCHdRgNTMvUUYdqMA_Bd4M4yVcsUrSJugUsKibrOjsTbTXSp7yv5BAUHenFs8s3sFIDOAK-gviJPFdCUhZktHP54gCnzhoPX0BCcDSQYq0bOfotqejnToFby87EQWqGVnBa4o8I0n6vaIdw9lg7gcox_Ee5G6UvdOeBRZ-TzUmfpyQi8VQN7viy4oOsnEtKrF1FpZuo0c99KCrdvH7PTNULtXwczSwVCCAMJ1YetfmTm4_titJMNbqbHEPFUadJ2KmyWci0knoKsAz4RabXku-wT2jlmpGbtD4LvjeLEAt4i4veBwg8hIqu0AwgHFH7ZXAG4jI9jnipUIGWGYg7X-ceRuamu9EQBU9Cc3uZSRFmBRhI25QJ9VvD0PF24p8qT6EARwfaIYosfXQTg1gw6QCKxP10__IYUAo2GHl1j7GZROz82OFNfiThHBpcZxSG4FoQwyMYxVitTj7SRFdRpott-lYK6XW2oRWOKJAERs_cyscGZ7ixyUAFGK_6hkqk5rps6qim_1QLZhUj0oj72sB1AwXLA78UmEXr6kX2rK2x0t6TdjvzwAKLhRNHE8EnJgbnnzGU7vu4gj6zHC0an5mP1YaKoCSotoitn-2utmi59i_ec8JOdVajCN-IRe-GIM9N7b6mnD7yaIBjVxePBOi32p_J5m29ssntTZccQAIlamwfzKh9BDzoWaruZw4_SyKNCtb_E5Kx3JfmsYklqguaB39TXpBz9MkBGba6iE2cGkMrI5sSYf-Okc3IYFK4e5SYQ3UMlpwKodVQayamdc94mcW5dzIMakuMZgFfILOY2L5nfo5eIv&isca=1";
 const src6="https://rr4---sn-uxax4vopj55gb-x1x6.googlevideo.com/videoplayback?expire=1669341116&ei=W8t_Y6S4OdSV-LAPoquvmAg&ip=2802%3A8010%3A4935%3A600%3Ad165%3A8001%3A3198%3Aacc3&id=o-AMi2Ryxl-shuyn2MjMdJkXiukAQsqdpH22IFkLFraJf-&itag=248&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C271%2C278%2C313&source=youtube&requiressl=yes&mh=Zv&mm=31%2C29&mn=sn-uxax4vopj55gb-x1x6%2Csn-x1xe7n76&ms=au%2Crdu&mv=m&mvi=4&pl=46&initcwndbps=702500&vprv=1&mime=video%2Fwebm&ns=Fk5ljj5hG7UyiZL0sCkBuv8J&gir=yes&clen=11299336&dur=169.335&lmt=1669235247423035&mt=1669319128&fvip=4&keepalive=yes&fexp=24001373%2C24007246&c=WEB&txp=3319224&n=42AuFaEVEcOH7Q&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRAIgF4953-hWVQr5OZUsk8jZZziGxQk1DDMC5IvCYcppxE0CIFkoBxGP9IlM1RMd6aBoV1qaVML6fuJesIIez6wgY8yD&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRgIhAJ-sgXn5XaAh_5bKm0lONvP5lUBEDdWeagZgYoxZETcmAiEAsUrTolBy0_9-2LjQpk16ZszQ3TVQrKI845aAyy3uqRE%3D&alr=yes&cpn=fYaskgmLpC1rnOGP&cver=2.20221122.06.00"
 const src7="https://rr4---sn-uxax4vopj55gb-x1x6.googlevideo.com/videoplayback?expire=1669341116&ei=W8t_Y6S4OdSV-LAPoquvmAg&ip=2802%3A8010%3A4935%3A600%3Ad165%3A8001%3A3198%3Aacc3&id=o-AMi2Ryxl-shuyn2MjMdJkXiukAQsqdpH22IFkLFraJf-&itag=251&source=youtube&requiressl=yes&mh=Zv&mm=31%2C29&mn=sn-uxax4vopj55gb-x1x6%2Csn-x1xe7n76&ms=au%2Crdu&mv=m&mvi=4&pl=46&initcwndbps=702500&vprv=1&mime=audio%2Fwebm&ns=Fk5ljj5hG7UyiZL0sCkBuv8J&gir=yes&clen=2680311&dur=169.361&lmt=1669235251873950&mt=1669319128&fvip=4&keepalive=yes&fexp=24001373%2C24007246&c=WEB&txp=3318224&n=42AuFaEVEcOH7Q&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRQIgXQ0ZM7iTaje0yOUYHhipZwNHOFXUPMm-BQZXYA-hMMgCIQDT0TpKyaypLWN3OnrzVv1bjEFbppY5wLmL7rjbWKdoIg%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRgIhAJ-sgXn5XaAh_5bKm0lONvP5lUBEDdWeagZgYoxZETcmAiEAsUrTolBy0_9-2LjQpk16ZszQ3TVQrKI845aAyy3uqRE%3D&alr=yes&cpn=fYaskgmLpC1rnOGP&cver=2.20221122.06.00"
 return (
    <View style={styles.root} >
      <BasicSurfaceView pause={false} src={src1} volume={"100"} seek={"90"}/>
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

