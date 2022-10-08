package com.react_native_mpv;

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

import com.react_native_mpv.MPVLib;

public class BasicSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private String TAG = "SurfaceView";
    private final ThemedReactContext context;
    SurfaceHolder holder;
    private Paint paint = new Paint();

    //Rewrite the constructor
    public BasicSurfaceView(ThemedReactContext context) {
        this(context, null);
    }

    public BasicSurfaceView(ThemedReactContext context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasicSurfaceView(ThemedReactContext context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        holder = this.getHolder();
        getHolder().addCallback(this);
        getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        /*
        this.setZOrderOnTop(true);
        this.setZOrderMediaOverlay(true);
        */

        this.setVisibility(0);
        
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();

        Log.i("surfaceView", "Created");

        MPVLib.create(context);
        MPVLib.setOptionString("force-window", "no");
        MPVLib.setOptionString("vo", "gpu");
        MPVLib.setOptionString("gpu-context", "android");
        MPVLib.init();
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

        String[] cmd = { "loadfile", "https://www.larmoire.info/jellyfish/media/jellyfish-3-mbps-hd-h264.mkv" };
        MPVLib.command(cmd);
    }

    // Called when the SurfaceView ends
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        Log.i("surface", "surface destroyed");
    }
}
