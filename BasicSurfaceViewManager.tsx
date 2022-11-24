import {requireNativeComponent} from 'react-native'
import { MutableRefObject } from 'react';
const BasicSurfaceViewManager = requireNativeComponent('RCTMpvView');

interface Props  {
    style?: object
    pause: boolean//true if want to pause, false if want to play
    src: string //path to video
    volume: string //volume of video, from 0 to 100 in a string way (ex: "50")
    seek?:string // seek specific time in video with percentage (ex: "50" )
   }
   
   const BasicSurfaceView = (props: Props) => {
    return <BasicSurfaceViewManager {...props} style={props.style} />;
   };

   
   export default BasicSurfaceView;