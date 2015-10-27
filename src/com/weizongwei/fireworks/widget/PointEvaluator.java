package com.weizongwei.fireworks.widget;

import android.animation.TypeEvaluator;

/**
 * Created by weizongwei on 15-10-27.
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y,startPoint.getColor());
        return point;
    }

}