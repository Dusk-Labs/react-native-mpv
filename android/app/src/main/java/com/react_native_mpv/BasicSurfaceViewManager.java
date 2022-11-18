package com.react_native_mpv;

import android.util.Log;
import android.view.Surface;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BasicSurfaceViewManager extends SimpleViewManager<BasicSurfaceView> {
    public static final String REACT_CLASS = "RCTMpvView";
    public static final String ComponentTag = "BasicSurfaceViewManager";
    ReactContext reactContext;
    ThemedReactContext themedContext;

    private static final String PROP_TEST = "test";

    public BasicSurfaceViewManager(ReactContext reactContext) {
        Log.i("surfaceViewManager", "Created");
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public BasicSurfaceView createViewInstance(ThemedReactContext reactContext) {
        this.themedContext = reactContext;
        /*
         * FrameLayout layout = new FrameLayout(reactContext);
         * layout.addView(new BasicSurfaceView(reactContext));
         * 
         * return layout;
         */
        return new BasicSurfaceView(reactContext);
    }

    @ReactProp(name = "radius")
    public void setRadius(BasicSurfaceView view, float value) {
        Log.i("surfaceViewManager", "setRadius");
        float radius = value;
        // view.invalidate();
    }

    @ReactProp(name = "pause")
    public void setTest(final BasicSurfaceView view, final Boolean pause) {
        if (pause == true) {
            view.pauseVideo();
        }
        if (pause == false) {
            view.playVideo();
        }
    }

    @ReactProp(name = "src")
    public void setUri(final BasicSurfaceView view, final String uri) {
        view.setUri(uri);
    }

}
