package com.romanprisyazhnuk.painterandroid;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PreviosPoint extends Point  {
    private Point neighbour;

    public PreviosPoint(final float x, final float y, final int col, final Point neighbour, final int width) {
        super(x, y, col, width);
        this.neighbour = neighbour;
    }

    @Override
    public void draw(final Canvas canvas, final Paint paint) {
        paint.setColor(color);
        paint.setStrokeWidth(width);
        canvas.drawLine(x, y, neighbour.x, neighbour.y, paint);
        canvas.drawCircle(x, y, width/2, paint);
    }
}
