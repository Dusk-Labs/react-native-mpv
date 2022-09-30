package com.react_native_mpv // replace your-app-name with your app’s name

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReactContext;


class MyAppPackage : ReactPackage {

      override fun createViewManagers(
      reactContext: ReactApplicationContext
  ) = listOf(BasicSurfaceViewManager(reactContext));

    override fun createNativeModules(
        reactContext: ReactApplicationContext
    ): List<NativeModule> = emptyList();
}
