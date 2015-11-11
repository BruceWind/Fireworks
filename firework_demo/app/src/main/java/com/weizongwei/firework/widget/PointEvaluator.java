package com.weizongwei.firework.widget;

import android.animation.TypeEvaluator;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by weizongwei on 15-10-27.
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        // > 1.上面这些仅仅足以完成上升和落下
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y, startPoint.getColor());



        // > 2.下落过程的  逐渐透明

        //本来不想加这个判断的  最初 只判断fraction>0.5的
        //但是发现颗粒向上的时候 fraction也会从0到1变化
        //所以加了这个Y 大小的比对 来判断是下落的过程
        if (endPoint.getY() > startPoint.getY()) {


            int alpha = Color.alpha(startPoint.getColor()); //1.得到当前的alpha
            if (fraction > 0.3f && alpha!=0 && fraction!=1.0f) {//

                alpha = Math.abs(alpha - (int) (255f * fraction));      //2.根据从0-1的值来设置alpha
                if (alpha < 0) {                                //3.如果计算出错 则重置这个值
                    alpha = 0 - alpha;
                    Log.e("alpha 计算","出错："+alpha
                    +" start alpha:"+Color.alpha(startPoint.getColor())
                    +" end alpha:"+Color.alpha(endPoint.getColor()));
                }

                int col = Color.argb(                           //4.重新生成color
                        alpha,
                        Color.red(endPoint.getColor()),
                        Color.green(endPoint.getColor()),
                        Color.blue(endPoint.getColor())
                );
                point = new Point(x, y, col);
            }

            if(fraction==1.0f)
            {
                point = new Point(x, y, Color.parseColor("#00000000"));
            }

        }
        return point;
    }

}