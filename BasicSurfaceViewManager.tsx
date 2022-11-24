import {requireNativeComponent} from 'react-native'
const BasicSurfaceViewManager = requireNativeComponent('RCTMpvView');

interface Props  {
    style?: object
    pause: boolean//true if want to pause, false if want to play
    src: string //path to video
    volume: string //volume of video, from 0 to 100 in a string way (ex: "50")
    seek?:string // seek specific time in video with percentage (ex: "50" means 50% of video duration)
   }
   
   const BasicSurfaceView = (props: Props) => {
    return <BasicSurfaceViewManager {...props} style={{flex:1}}/>;//flex:1 is important, otherwise it will have problems wih JNI, don't know why
   };

   
   export default BasicSurfaceView;