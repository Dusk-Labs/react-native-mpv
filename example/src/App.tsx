import React, { useState, useEffect, useCallback } from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { VideoPlayer } from 'react-native-mpv';

export default function App() {
  const [currentTime, setCurrentTime] = useState(0);
  const [isPaused, setPaused] = useState(false);
  const onProgress = useCallback((event: {currentTime: number}) => {
    setCurrentTime(event.currentTime);
  });

  useEffect(() => {
    if (currentTime > 5) setPaused(true);
  }, [currentTime, setPaused]);
  
  return (
    <View style={styles.container}>
      <VideoPlayer url="http://www.larmoire.info/jellyfish/media/jellyfish-40-mbps-hd-h264.mkv" hwdec="mediacodec-copy" paused={false} onProgress={onProgress} style={styles.box} />
      <Text style={styles.text}>Current Time: {currentTime}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    width: "100%",
    height: "100%",
  },
  box: {
    flex: 1,
    overflow: "hidden",
    width: "100%",
    height: "100%",
  },
  text: {
    position: "absolute",
    zIndex: 2,
    color: "red",
    fontSize: 30,
    margin: 100,
  },
});
