package com.weizongwei.fireworks.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import com.weizongwei.fireworks.utils.ViewPickColorsUtil;

import java.util.ArrayList;

/**
 * Created by weizongwei on 15-10-24.
 *
 * 自带烟花效果的view
 */
public class FireWorksView extends View {


    public static final float RADIUS = (int)(5* Resources.getSystem().getDisplayMetrics().density+0.5f);

    private View paintView;
    private int top,height,left,width;

    ArrayList<Integer> colorList;

    public FireWorksView(Context context) {
        super(context);
        init();
    }

    public FireWorksView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FireWorksView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }



    public  void setPaintView(View v)
    {
        paintView=v;
        top=v.getTop();
        height=v.getBottom()-top;
        left=v.getLeft();
        width=v.getRight()-left;
    }


    public static FireWorksView add2RootView(Activity activity) {

        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        FireWorksView surfview = new FireWorksView(activity);

        rootView.addView(
                surfview,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );


        return surfview;
    }

    private  void init()
    {


    }

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);


        if(paintView!=null) {

            colorList = ViewPickColorsUtil.radomPickColors(paintView);
            // 创建画笔
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            //设置锯齿效果

            try {
                for (Integer int_col : colorList) {
                    Log.d("XXXXX", "col:" + int_col);

                    // 设置颜色
                    paint.setColor(int_col);
                    int l=left + ViewPickColorsUtil.getRadomInt(width);
                    int b=top + ViewPickColorsUtil.getRadomInt(height);

                    canvas.drawCircle(
                            l,
                            b,
                            RADIUS,//radius
                            paint
                    );

                }
            } catch (Exception ex) {
                Log.e("CCCCC", ex.toString());
            }


        }

    }


    /*自定义线程*/
    class PaintThread implements Runnable{
        Canvas canvas;
        public PaintThread(Canvas c)
        {
            canvas=c;
        }

        public void run() {

            colorList=ViewPickColorsUtil.radomPickColors(paintView);
            paintView.setVisibility(GONE);
            // 创建画笔
            Paint paint = new Paint();
            try {
                for (Integer int_col:colorList)
                {
                    Log.d("XXXXX","col:"+int_col);

                    // 设置颜色
                    paint.setColor(int_col);

                    canvas.drawCircle(
                            left+ViewPickColorsUtil.getRadomInt(width),
                            top+ViewPickColorsUtil.getRadomInt(height),
                            10,//radius
                            paint
                    );

                }
            }
            catch(Exception ex)
            {
                Log.e("",ex.toString());
            }


        }

    }

}
