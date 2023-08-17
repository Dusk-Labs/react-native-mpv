package com.mpv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;

import com.mpv.MPVLib;
import com.mpv.MPVLib.mpvFormat;
import com.mpv.VideoEventObserver;

public class VideoSurface extends SurfaceView implements SurfaceHolder.Callback {
  private String TAG = "SurfaceView";
  private final ThemedReactContext context;
  private SurfaceHolder holder;
  private Paint paint = new Paint();
  private String videoUrl = "https://www.larmoire.info/jellyfish/media/jellyfish-3-mbps-hd-h264.mkv";
  private VideoEventObserver observer;
  private Boolean surfaceAttached = false;

  public VideoSurface(ThemedReactContext context) {
    this(context, null);
  }

  public VideoSurface(ThemedReactContext context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public VideoSurface(ThemedReactContext context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.context = context;
    this.observer = new VideoEventObserver(context);

    holder = this.getHolder();
    getHolder().addCallback(this);
    getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    this.setVisibility(0);

    setFocusable(true);
    setFocusableInTouchMode(true);
    requestFocus();

    Log.i("surfaceView", "Created");

    MPVLib.create(context);

    // Register our event observer
    MPVLib.addObserver(this.observer);
    this.observerProperties();

    MPVLib.setOptionString("force-window", "no");
    MPVLib.setOptionString("vo", "gpu");
    MPVLib.setOptionString("gpu-context", "android");
    MPVLib.setOptionString("hwdec", "auto");
    MPVLib.setOptionString("opengl-es", "yes");
    MPVLib.setOptionString("hwdec-codecs", "h264,hevc,mpeg4,mpeg2video,vp8,vp9,av1");
    MPVLib.setOptionString("ao", "audiotrack,opensles");
    MPVLib.init();
  }

  private void observerProperties() {
    MPVLib.observeProperty("time-pos", mpvFormat.MPV_FORMAT_INT64);
    MPVLib.observeProperty("duration", mpvFormat.MPV_FORMAT_INT64);
    MPVLib.observeProperty("pause", mpvFormat.MPV_FORMAT_FLAG);
    MPVLib.observeProperty("track-list", mpvFormat.MPV_FORMAT_NONE);
    MPVLib.observeProperty("video-params", mpvFormat.MPV_FORMAT_NONE);
    MPVLib.observeProperty("playlist-pos", mpvFormat.MPV_FORMAT_INT64);
    MPVLib.observeProperty("playlist-count", mpvFormat.MPV_FORMAT_INT64);
    MPVLib.observeProperty("video-format", mpvFormat.MPV_FORMAT_NONE);
    MPVLib.observeProperty("media-title", mpvFormat.MPV_FORMAT_STRING);
    MPVLib.observeProperty("metadata/by-key/Artist", mpvFormat.MPV_FORMAT_STRING);
    MPVLib.observeProperty("metadata/by-key/Album", mpvFormat.MPV_FORMAT_STRING);
    MPVLib.observeProperty("loop-playlist", mpvFormat.MPV_FORMAT_NONE);
    MPVLib.observeProperty("loop-file", mpvFormat.MPV_FORMAT_NONE);
    MPVLib.observeProperty("shuffle", mpvFormat.MPV_FORMAT_FLAG);
    MPVLib.observeProperty("hwdec-current", mpvFormat.MPV_FORMAT_NONE);
    MPVLib.observeProperty("cache-buffering-state", mpvFormat.MPV_FORMAT_INT64);
    MPVLib.observeProperty("paused-for-cache", mpvFormat.MPV_FORMAT_FLAG);
    MPVLib.observeProperty("eof-reached", mpvFormat.MPV_FORMAT_NONE);
  }

  @Override // call when switching between horizontal and vertical
  public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    Log.i(TAG, "surface changed");
    MPVLib.setPropertyString("android-surface-size", arg2 + "x" + arg3);
  }

  @Override
  public void onWindowVisibilityChanged(int visibility) {
    Log.i("surface", "visibility changed to: " + visibility);
    super.onWindowVisibilityChanged(visibility);
  }

  // Called when the SurfaceView is created
  @Override
  public void surfaceCreated(SurfaceHolder arg0) {
    Log.i("surfaceView", "Initialized");
    MPVLib.attachSurface(holder.getSurface());
    MPVLib.setOptionString("force-window", "yes");
    MPVLib.setOptionString("loop-file", "inf");

    // If the surface has just been attached we want to play this.videoUrl
    String[] cmd = { "loadfile", this.videoUrl };
    MPVLib.command(cmd);

    this.setKeepScreenOn(true);
    this.surfaceAttached = true;
  }

  // Called when the SurfaceView ends
  @Override
  public void surfaceDestroyed(SurfaceHolder arg0) {
    Log.i("surface", "surface destroyed");
    MPVLib.setPropertyString("vo", "null");
    MPVLib.setOptionString("force-window", "no");
    MPVLib.detachSurface();
    this.surfaceAttached = false;
  }

  public void setVideoUrl(String url) {
    this.videoUrl = url;

    // If we loadfile before the surface is attached, we crash
    if (this.surfaceAttached) {
      String[] cmd = { "loadfile", this.videoUrl };
      MPVLib.command(cmd);
    }
  }

  public void setHwdec(String hwdec) {
    MPVLib.setOptionString("hwdec", hwdec);
  }

  public void setPaused(Boolean paused) {
    Log.i("surface", "Setting paused to " + paused);
    MPVLib.setPropertyBoolean("pause", !!paused);
  }
}
