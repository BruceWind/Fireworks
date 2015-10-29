package com.weizongwei.fireworks.widget;

import android.animation.TimeInterpolator;

/**
 * Created by weizongwei on 15-10-27.
 * 根据时间 逐渐改变数值  并且是曲线型变化  
 */
public class DecelerateAccelerateInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input)) / 2;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }
        return result;
    }

}