package com.romanprisyazhnuk.painterandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    private DrawView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawView = new DrawView(this);
        setContentView(drawView);
        drawView.requestFocus();
    }
    public class DrawView extends View implements View.OnTouchListener {
        //String sDown;
        //String sMove;

        private List<Point> points = new ArrayList<Point>();
        private Paint paint;
        private Random random;
        private int color;
        private int[] colors = new int[]{Color.WHITE, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA,
                Color.RED, Color.YELLOW, Color.BLACK};
        private int width;

        public DrawView(Context context) {
            super(context);
            width = 15;
            this.random = new Random();
            setFocusable(true);
            setFocusableInTouchMode(true);
            this.setOnTouchListener(this);
            this.paint = new Paint();
            randomColour();
            paint.setAntiAlias(true);
        }

        public void clearPoints () {
            points.clear();
            invalidate();
        }


        public void randomColour(){
            this.color = colors[random.nextInt(colors.length)];
        }

        @Override
        public void onDraw(Canvas canvas) {
            for (Point point : points) {
                point.draw(canvas, paint);
            }
        }

        public boolean onTouch(View view, MotionEvent event) {
            Point point;

            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    //sDown = "Down: " + x + "," + y;
                    //sMove = "";
                    point = new Point(x, y, color, width);
                    break;
                case MotionEvent.ACTION_MOVE:
                    //sMove = "Move: " + x + "," + y;
                    point = new PreviosPoint(x, y, color, points.get(points.size() - 1), width);
                    break;
                default:
                    return false;
            }
            points.add(point);
            invalidate();
            //setText(sDown + "\n" + sMove + "\n" + sUp);
            return true;
        }
    }
}
