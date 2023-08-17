package com.mpv;

import android.graphics.Color;
import android.widget.FrameLayout;
import android.view.View;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.mpv.VideoSurface;

public class MpvViewManager extends SimpleViewManager<FrameLayout> {
  public static final String REACT_CLASS = "MpvView";

  @Override
  @NonNull
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  @NonNull
  public FrameLayout createViewInstance(ThemedReactContext reactContext) {
    FrameLayout layout = new FrameLayout(reactContext);
    layout.addView(new VideoSurface(reactContext), 0);

    return layout;
  }

  private VideoSurface getSurface(FrameLayout layout) {
    View child = layout.getChildAt(0);

    if (child instanceof VideoSurface) {
      return (VideoSurface) child;
    }

    return null;
  }

  @ReactProp(name = "url")
  public void setUrl(FrameLayout layout, String url) {
    getSurface(layout).setVideoUrl(url);
  }

  @ReactProp(name = "hwdec")
  public void setHwdec(FrameLayout layout, String hwdec) {
      getSurface(layout).setHwdec(hwdec);
  }

  @ReactProp(name = "paused")
  public void setPaused(FrameLayout layout, Boolean paused) {
      getSurface(layout).setPaused(paused);
  }
}
