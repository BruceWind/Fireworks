package com.weizongwei.fireworks.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import com.weizongwei.fireworks.utils.ViewPickColorsUtil;

import java.util.ArrayList;

/**
 * Created by weizongwei on 15-10-24.
 */
public class SurfaceFireDrawView extends View  implements SurfaceHolder.Callback{



    private View paintView;
    private int top,height,left,width;

    ArrayList<Integer> colorList;

    public SurfaceFireDrawView(Context context) {
        super(context);
        //init();
    }

    public SurfaceFireDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init();
    }

    public SurfaceFireDrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //init();
    }

    public  void setPaintView(View v)
    {
        paintView=v;
        top=v.getTop();
        height=v.getBottom()-top;
        left=v.getLeft();
        width=v.getRight()-left;
    }


    public static SurfaceFireDrawView add2RootView(Activity activity) {

        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        SurfaceFireDrawView surfview = new SurfaceFireDrawView(activity);

        rootView.addView(
                surfview,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );


        return surfview;
    }

/*    private  void init()
    {
        holder = this.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);  //顶层绘制SurfaceView设成透明
        this.setZOrderOnTop(true);

    }*/

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);


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
                            15,//radius
                            paint
                    );
                    //本身是不需要在ui线程睡眠的  不过为了防止radom传入的时间戳种子都相同了 所以先沉睡一ms
                    //Thread.sleep(2);


                }
            } catch (Exception ex) {
                Log.e("CCCCC", ex.toString());
            }


        }

    }

        @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

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
