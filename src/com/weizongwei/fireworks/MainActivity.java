package com.weizongwei.fireworks;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.weizongwei.fireworks.utils.ViewPickColorsUtil;
import com.weizongwei.fireworks.widget.SurfaceFireDrawView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    SurfaceFireDrawView surface_main;
    TextView txt_main;
    ArrayList<Integer> arrayList;


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
        surface_main=(SurfaceFireDrawView)findViewById(R.id.sur_draw);
        txt_main=(TextView)findViewById(R.id.txt_main);
    }

    private void startDraw()
    {
        surface_main.setPaintView(txt_main);

        surface_main.invalidate();



    }




}
