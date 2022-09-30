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

        this.setZOrderOnTop(true);
        /*
        this.setZOrderMediaOverlay(true);
        */

        this.setVisibility(0);
        
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();

        Log.i("surfaceView", "Created");
    }

    @Override // call when switching between horizontal and vertical
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        Log.i("surface", "surface changed");
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
        // Start the thread when creating
        int x = 0;
        int y = 0;
        int dx = 2;
        int dy = 3;
        int width = 30;
        int height = 30;
        Canvas canvas = arg0.lockCanvas();
        canvas.drawARGB(255, 255, 168, 0);
        // Lock the brush object
        // Set the brush
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        // Fill the background color
        Rect rect = new Rect(0, 0, this.getWidth(), this.getHeight());
        canvas.drawRect(rect, paint);

        // draw a small ball, set the color of the ball to red
        paint.setColor(Color.RED);
        RectF rf = new RectF(x, y, x + width, y + height);
        canvas.drawOval(rf, paint);

        Rect bounds = canvas.getClipBounds();
        Log.i("surface", "bounds: " + bounds);

        holder.unlockCanvasAndPost(canvas);
    }

    // Called when the SurfaceView ends
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        Log.i("surface", "surface destroyed");
    }
}
