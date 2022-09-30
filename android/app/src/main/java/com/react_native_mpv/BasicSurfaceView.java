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
import com.facebook.react.bridge.ReactContext;

public class BasicSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder;
    private boolean runFlag;
    private Paint paint = new Paint();

    public BasicSurfaceView(ReactContext context) {
        super(context);
        // SurfaceHolder holder = this.getHolder();
        holder = getHolder();
        holder.addCallback(this);
        Log.i("surfaceView", "Created");
    }

    @Override // call when switching between horizontal and vertical
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        Log.i("surfaceChangedMethod", "Changed");

    }

    // Called when the SurfaceView is created
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // Start the thread when creating
        Log.i("surfaceCreatedMethod", "Created");
        runFlag = true;
        int x = 0;
        int y = 0;
        int dx = 2;
        int dy = 3;
        int width = 30;
        int height = 30;
        Canvas canvas = arg0.lockCanvas();
        canvas.drawARGB(255, 255, 0, 0);
        // holder = this.getHolder();
        // holder.addCallback(this);
        // Lock the brush object
        // Set the brush
        // paint.setColor(Color.WHITE);
        // paint.setStyle(Paint.Style.FILL);
        // Fill the background color
        // Rect rect = new Rect(0, 0, this.getWidth(), this.getHeight());
        // canvas.drawRect(rect, paint);

        // draw a small ball, set the color of the ball to red
        // paint.setColor(Color.RED);
        // RectF rf = new RectF(x, y, x + width, y + height);
        // canvas.drawOval(rf, paint);
        // Rect bounds = canvas.getClipBounds();
        // Log.i("surface", "bounds: " + bounds);
    }

    // Called when the SurfaceView ends
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // End the thread when exiting
        runFlag = false;
        Log.i("surfaceDestroyecMethod", "Destroyed");

    }
}
