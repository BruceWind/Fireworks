package com.weizongwei.fireworks.widget;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import java.util.Random;

/**
 * Created by weizongwei on 15-10-27.
 */
public class BangView extends View {

    public static final int RADIUS = (int)(5* Resources.getSystem().getDisplayMetrics().density+0.5f);

    private Point currentPoint;

    private int upHeight = (int)(40* Resources.getSystem().getDisplayMetrics().density+0.5f);

    private Paint mPaint;
    private View paintView;
    private int top,height,left,width,xcener;

    public BangView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    public BangView(Activity activity) {
        super(activity);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(left, top);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }


    public  void setPaintView(View v)
    {
        paintView=v;
        top=v.getTop();
        height=v.getBottom()-top;
        left=v.getLeft();
        width=v.getRight()-left;

        xcener=left+width/2;
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void shake()
    {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(350);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            Random random = new Random();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                paintView.setTranslationX((random.nextFloat() - 0.5f) * paintView.getWidth() * 0.05f);
                paintView.setTranslationY((random.nextFloat() - 0.5f) * paintView.getHeight() * 0.05f);

            }
        });
        animator.start();
        paintView.animate().setDuration(350).setStartDelay(100).scaleX(0f).scaleY(0f).alpha(0f).start();
    }

    public void startAnimation() {

        shake();

        Point startPoint = new Point(left+RADIUS, top+RADIUS);
        Point endPoint = new Point(left+RADIUS, top-RADIUS-upHeight);

        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint,startPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setInterpolator(new DecelerateAccelerateInterpolator());
        anim.setDuration(1000);
        anim.start();
    }


    public static BangView add2RootView(Activity activity) {

        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        BangView surfview = new BangView(activity);

        rootView.addView(
                surfview,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );


        return surfview;
    }
}