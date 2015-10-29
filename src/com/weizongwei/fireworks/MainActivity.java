package com.weizongwei.fireworks;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import com.weizongwei.fireworks.widget.BoomView;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    BoomView surface_main;
    ImageView img_pick;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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

    }




}
