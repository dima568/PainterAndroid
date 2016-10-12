package com.romanprisyazhnuk.painterandroid;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Point {
    public float x, y;
    public int color;
    public int width;

    public Point( float x, float y, int color, int width) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
    }

    public void draw(final Canvas canvas, final Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(x, y, width/2, paint);
    }

    @Override
    public String toString()
    {
        return "x = "+x+", y = "+y+", color: "+color+", width: "+width+";";
    }
}

