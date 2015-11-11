package com.weizongwei.firework.widget;

/**
 * Created by weizongwei on 15-10-27.
 */
public class Point {

    private float x;

    private float y;

    private int color;

    public Point(float x, float y,int color) {
        this.x = x;
        this.y = y;
        this.color=color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getColor() {
        return color;
    }
}