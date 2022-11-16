package com.react_native_mpv;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is
   * used to schedule
   * rendering of the component.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    hideNavigationBar();
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) {
      hideNavigationBar();
    }
  }

  private void hideNavigationBar() {
    getWindow().getDecorView().setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN);
  }

  @Override
  protected String getMainComponentName() {
    return "react_native_mpv";
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. There the RootView
   * is created and
   * you can specify the renderer you wish to use - the new renderer (Fabric) or
   * the old renderer
   * (Paper).
   */
  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new MainActivityDelegate(this, getMainComponentName());
  }

  public static class MainActivityDelegate extends ReactActivityDelegate {
    public MainActivityDelegate(ReactActivity activity, String mainComponentName) {
      super(activity, mainComponentName);
    }

    @Override
    protected ReactRootView createRootView() {
      ReactRootView reactRootView = new ReactRootView(getContext());
      return reactRootView;
    }

    @Override
    protected boolean isConcurrentRootEnabled() {
      return false;
    }
  }
}
