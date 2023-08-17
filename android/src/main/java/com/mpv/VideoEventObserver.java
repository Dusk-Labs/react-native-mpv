package com.mpv;

import android.util.Log;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.Arguments;
import com.mpv.MPVLib;
import com.mpv.MPVLib.mpvEventId;

public class VideoEventObserver implements MPVLib.EventObserver {
  private String TAG = "VideoEventObserver";
  private final ThemedReactContext reactContext;
  private long cacheLevel = 0;

  public VideoEventObserver(ThemedReactContext reactContext) {
    this.reactContext = reactContext;
  }

  private void emitCurrentTimeEvent(long currentTime) {
    WritableMap event = Arguments.createMap();
    event.putInt("time", (int)currentTime);

    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit("currentTime", event);
  }

  private void emitCacheBufferingState(boolean state) {
    WritableMap event = Arguments.createMap();
    event.putInt("bufferLevel", (int)this.cacheLevel);
    event.putBoolean("isBuffering", state);

    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit("buffering", event);
  }

  private void emitVideoDuration(long duration) {
    WritableMap event = Arguments.createMap();
    event.putInt("duration", (int)duration);

    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit("duration", event);
  }

  private void emitStreamEnd() {
    reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit("eof-reached", Arguments.createMap());
  }

  public void eventProperty(String prop) {
    Log.i(TAG, "eventProperty() called with prop: " + prop);

    switch (prop) {
      case "eof-reached":
        this.emitStreamEnd();
        break;
      default: break;
    }
  }

  public void eventProperty(String prop, long value) {
    Log.i(TAG, "eventProperty(long) called with prop: " + prop);
    Log.i(TAG, "eventProperty(long) called with value: " + value);

    switch (prop) {
      case "time-pos":
        this.emitCurrentTimeEvent(value);
        break;
      case "cache-buffering-state":
        this.cacheLevel = value;
        break;
      case "duration":
        this.emitVideoDuration(value);
        break;
      default: break;
    }
  }

  public void eventProperty(String prop, boolean value) {
    Log.i(TAG, "eventProperty(boolean) called with prop: " + prop);
    Log.i(TAG, "eventProperty(boolean) called with value: " + value);

    switch (prop) {
      case "paused-for-cache":
        this.emitCacheBufferingState(value);
        break;
      default: break;
    }
  }

  public void eventProperty(String prop, String value) {
    Log.i(TAG, "eventProperty(String) called with prop: " + prop);
    Log.i(TAG, "eventProperty(String) called with value: " + value);
  }

  public void event(int eventId) {
    Log.i(TAG, "event() called with id: " + eventId);
    switch (prop) {
      case mpvEventId.MPV_EVENT_FILE_LOADED:
        this.emitFileLoaded();
        break;
      default: break;
    }
  }
}
