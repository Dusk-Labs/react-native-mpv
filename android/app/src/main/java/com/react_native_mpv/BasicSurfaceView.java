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
//import android.content.pm.ActivityInfo;

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

    // Rewrite the constructor
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
        // getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        this.setVisibility(0);

        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();

        Log.i("surfaceView", "Created");

        MPVLib.create(context);

        MPVLib.setOptionString("force-window", "no");
        MPVLib.setOptionString("vo", "gpu");
        MPVLib.setOptionString("gpu-context", "android");
        MPVLib.setPropertyString("loop-file", "inf");
        MPVLib.setOptionString("hwdec", "mediacodec-copy");
        MPVLib.setOptionString("hwdec-codecs", "h264,hevc,mpeg4,mpeg2video,vp8,vp9");
        MPVLib.setOptionString("vd-lavc-dr", "no");
        MPVLib.setOptionString("persistent-overlay", "yes");
        MPVLib.setOptionString("load-stats-overlay", "yes");

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
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        MPVLib.attachSurface(holder.getSurface());
        MPVLib.setOptionString("force-window", "yes");

        String[] cmd = { "script-binding", "stats/display-stats-toggle" };
        MPVLib.command(cmd);

        // MPVLib.setOptionString("start", "50%");// works
        // MPVLib.setOptionString("speed", "50"); // works
        // MPVLib.setOptionString("geometry", "50%");// not working

        String[] cmd2 = { "loadfile",
                "https://www.larmoire.info/jellyfish/media/jellyfish-40-mbps-hd-hevc-10bit.mkv" };
        // https://www.larmoire.info/jellyfish/media/jellyfish-40-mbps-hd-hevc-10bit.mkv
        // https://rr6---sn-uxax4vopj55gb-x1xee.googlevideo.com/videoplayback?expire=1666832045&ei=TYJZY5eKMYqE-LAP-fSkwAM&ip=186.130.71.76&id=o-AC6UFqF42fJ6o0LplSxryidf2gyPjExX_T76H_ywiedt&itag=244&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C271%2C278%2C313&source=youtube&requiressl=yes&mh=Zd&mm=31%2C29&mn=sn-uxax4vopj55gb-x1xee%2Csn-x1x7dnez&ms=au%2Crdu&mv=m&mvi=6&pl=20&initcwndbps=868750&vprv=1&mime=video%2Fwebm&ns=5Rktz37dz_8h6cJd4MrrZZMI&gir=yes&clen=7617088&dur=375.575&lmt=1666803083337019&mt=1666810153&fvip=5&keepalive=yes&fexp=24001373%2C24007246&c=WEB&txp=3319224&n=OgQ1DNRh_pp6dg&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRAIgQU6q1WeIY16lN4-OtraLXF5vCOhn2YnX_gIbxMHRepYCIHbP3swiie9DKCxDS1kGTfZdDZSOH1RsY-qMpqbrB2eG&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRgIhAJaNpIkWte5CiNRdWfStKV_s-qIumiwXqIi5Jqe8tvRAAiEAlkFqJOpQfLq-_rymli33P8HcJIZpYlgdab7SNCxCwbU%3D&alr=yes&cpn=HiChUNgHQRPCc1yI&cver=2.20221024.10.00&range=0-170246&rn=1&rbuf=0&altitags=243%2C242
        MPVLib.command(cmd2);

        // String[] cmd3 = { "show-progress" }; // not showing anything
        // MPVLib.command(cmd3);

        // String[] cmd4 = { "stop" };// not playback
        // MPVLib.command(cmd4);
        // String[] cmd5 = { "audio-add" };
        // MPVLib.command(cmd5);

    }

    // Called when the SurfaceView ends
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        Log.i("surface", "surface destroyed");
        MPVLib.setPropertyString("vo", "null");
        MPVLib.setOptionString("force-window", "no");
        MPVLib.detachSurface();
    }

    public void pause() {
        MPVLib.command(new String[] { "cycle", "pause" });
    }

    public void stop() {
        MPVLib.command(new String[] { "stop" });
    }

    public void test(String value) {
        Log.i("value arrived to test method", value);
        if (value.equals("pause")) {
            Log.i("test", "pause");
        } else if (value.equals("play")) {
            Log.i("test", "play");
        } else {
            Log.i("test", "none");
        }
    }

    public void loadUrl(String url) {
        String[] cmd2 = { "loadfile",
                url };
        // https://www.larmoire.info/jellyfish/media/jellyfish-40-mbps-hd-hevc-10bit.mkv
        // https://rr6---sn-uxax4vopj55gb-x1xee.googlevideo.com/videoplayback?expire=1666832045&ei=TYJZY5eKMYqE-LAP-fSkwAM&ip=186.130.71.76&id=o-AC6UFqF42fJ6o0LplSxryidf2gyPjExX_T76H_ywiedt&itag=244&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C271%2C278%2C313&source=youtube&requiressl=yes&mh=Zd&mm=31%2C29&mn=sn-uxax4vopj55gb-x1xee%2Csn-x1x7dnez&ms=au%2Crdu&mv=m&mvi=6&pl=20&initcwndbps=868750&vprv=1&mime=video%2Fwebm&ns=5Rktz37dz_8h6cJd4MrrZZMI&gir=yes&clen=7617088&dur=375.575&lmt=1666803083337019&mt=1666810153&fvip=5&keepalive=yes&fexp=24001373%2C24007246&c=WEB&txp=3319224&n=OgQ1DNRh_pp6dg&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRAIgQU6q1WeIY16lN4-OtraLXF5vCOhn2YnX_gIbxMHRepYCIHbP3swiie9DKCxDS1kGTfZdDZSOH1RsY-qMpqbrB2eG&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRgIhAJaNpIkWte5CiNRdWfStKV_s-qIumiwXqIi5Jqe8tvRAAiEAlkFqJOpQfLq-_rymli33P8HcJIZpYlgdab7SNCxCwbU%3D&alr=yes&cpn=HiChUNgHQRPCc1yI&cver=2.20221024.10.00&range=0-170246&rn=1&rbuf=0&altitags=243%2C242
        MPVLib.command(cmd2);
    }

    public void pauseVideo() {
        MPVLib.setPropertyBoolean("pause", true);
    }

    public void playVideo() {
        MPVLib.setPropertyBoolean("pause", false);
    }
}
