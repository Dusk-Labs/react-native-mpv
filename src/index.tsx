import React, { useState, useEffect } from 'react';
import {
  requireNativeComponent,
  UIManager,
  Platform,
  NativeEventEmitter,
  NativeModules,
  StyleSheet,
  View,
  type ViewStyle,
} from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-mpv' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

type MpvProps = {
  url?: string;
  hwdec?: string;
  style: ViewStyle;
  // Control whether playback should be paused or not
  paused?: boolean,
  // Callback for when progress in the video is made
  onProgress?: (event: { currentTime: number }) => void;
  // Callback for when the video is buffering
  onBuffer?: (event: { isBuffering: boolean, bufferLevel: number }) => void;
};

const ComponentName = 'MpvView';

const MpvView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<MpvProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

export const VideoEvents = new NativeEventEmitter(NativeModules.MpvView);

export const VideoPlayer: React.FC<MpvProps> = ({url, onProgress, onBuffer, paused = false}) => {
  const [currentTime, setCurrentTime] = useState(0);
  const [isBuffering, setIsBuffering] = useState(false);
  const [bufferLevel, setBufferLevel] = useState(0);

  useEffect(() => {
    if (!onProgress) return;
    onProgress({ currentTime });
  }, [currentTime, onProgress]);

  useEffect(() => {
    if (!onBuffer) return;
    onBuffer({ isBuffering, bufferLevel });
  }, [isBuffering, bufferLevel, onBuffer]);

  useEffect(() => {
    let eventListener = VideoEvents.addListener('currentTime', event => {
      setCurrentTime(event.time);
    });

    let bufferingListener = VideoEvents.addListener('buffering', event => {
      setIsBuffering(event.isBuffering);
      setBufferLevel(event.bufferLevel);
    });

    return () => {
      eventListener.remove();
      bufferingListener.remove();
    };
  }, [setCurrentTime, setIsBuffering, setBufferLevel]);

  return (
    <View style={styles.container}>
      <MpvView 
        url={url} 
        hwdec="mediacodec-copy" 
        paused={paused} 
        style={styles.player} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  player: {
    flex: 1,
    overflow: "hidden",
    width: "100%",
    height: "100%",
  },
});
