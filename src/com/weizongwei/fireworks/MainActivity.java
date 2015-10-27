package com.weizongwei.fireworks;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.weizongwei.fireworks.widget.BangView;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    BangView surface_main;
    ImageView txt_main;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initWidget();
        txt_main.postDelayed(new Runnable() {
            @Override
            public void run() {
                startDraw();
            }
        }, 1600);
    }

    private  void initWidget()
    {
        txt_main=(ImageView)findViewById(R.id.txt_main);
    }

    private void startDraw()
    {
        surface_main= BangView.add2RootView(this);
        surface_main.setPaintView(txt_main);
        surface_main.invalidate();
        surface_main.startAnimation();

    }




}
