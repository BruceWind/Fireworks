package com.weizongwei.firework.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import com.weizongwei.firework.widget.BoomView;


public class MainActivity extends Activity {

    BoomView surface_main;
    ImageView img_pick;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        img_pick.postDelayed(new Runnable() {
            @Override
            public void run() {
                startDraw();
            }
        }, 1600);
    }

    private  void initWidget()
    {
        img_pick=(ImageView)findViewById(R.id.img_pick);
    }

    private void startDraw()
    {
        surface_main= BoomView.add2RootView(this);
        surface_main.setPaintView(img_pick);

        //surface_main.setEnableViewShakeAnim(false);
        surface_main.startAnimation();

        Log.d("DDDDDD",
                "scaledDensity = " + Resources.getSystem().getDisplayMetrics().scaledDensity
                        + " DENSITY_DEFAULT=" + DisplayMetrics.DENSITY_DEFAULT
                        + " DENSITY_DEVICE=" + Resources.getSystem().getDisplayMetrics().density);
        //由于系统定制化，隐藏了getDeviceDensity()方法和DENSITY_DEVICE这个常量，所以无法直接打印出来，但是源码中xdpi正好被这个常亮赋值了，
        //所以这里用xdpi代替了方法和DENSITY_DEVICE；
    }
}
