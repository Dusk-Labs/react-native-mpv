import {requireNativeComponent} from 'react-native'
import { MutableRefObject } from 'react';
const BasicSurfaceViewManager = requireNativeComponent('RCTMpvView');
//module.exports = requireNativeComponent('RCTMpvView');

interface Props  {
    style?: object
    ref: MutableRefObject<undefined>
    radius: number
    test: string
   }
   
   const BasicSurfaceView = (props: Props) => {
    return <BasicSurfaceViewManager {...props} style={props.style} />;
   };

   
   export default BasicSurfaceView;