package com.romanprisyazhnuk.painterandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    private DrawView drawView;
    private Point[] touchPoints = new Point[10];
    private Point[] prevTouchPoints = new Point[10];
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
        public void onDraw(Canvas canvas)
        {
                for (Point point : points) {
                    if(point!=null){
                        point.draw(canvas, paint);
                    }
                }

        }

        public boolean onTouch(View view, MotionEvent event) {

//            touchPoints = new Point[10];
//            prevTouchPoints = new Point[10];
            int actionMask = event.getActionMasked();

            int touchNum;
            switch (actionMask){

                case MotionEvent.ACTION_DOWN:
                     touchNum = event.getActionIndex();
                    touchPoints[touchNum] = new Point(event.getX(touchNum), event.getY(touchNum), color, width);
                    prevTouchPoints[touchNum] = touchPoints[touchNum];
                    Log.v("MYTAG", "ACTION_DOWN works! Point "+ touchNum+" added!");
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    touchNum = event.getActionIndex();
                    touchPoints[touchNum] = new Point(event.getX(touchNum), event.getY(touchNum), color, width);
                    prevTouchPoints[touchNum] = touchPoints[touchNum];
                    Log.v("MYTAG", "ACTION_POINTER_DOWN works! Point "+ touchNum+" added!");
                    Log.v("MYTAG", touchPoints[touchNum].toString());
                    break;
                case MotionEvent.ACTION_MOVE:
//                    Log.v("MYTAG", "ACTION_MOVE works!");
                    for (int i = 0; i < 10; i++) {
//                        Log.v("MYTAG", "point "+i+" checked");
//                        Log.v("MYTAG", touchPoints[i].toString());
                        try {
                            float x = event.getX(i);
                            if (touchPoints[i] != null && event.getPointerCount() >= 1) {
                                touchPoints[i] = new PreviosPoint(event.getX(i), event.getY(i), color, prevTouchPoints[i], width);
                                prevTouchPoints[i] = new Point(event.getX(i), event.getY(i), color, width);
//                            touchPoints[i] = new Point(event.getX(i), event.getY(i), color, width);
                                Log.v("MYTAG", "point "+i+" rewrited (x = "+event.getX(i)+"; y = "+ event.getY(i)+")");
                            }
                            else{}
                        }
                        catch (Exception ex){

                        }

                    }
                    break;
                case MotionEvent.ACTION_UP:{
                    touchPoints = new Point[10];
                    prevTouchPoints = new Point[10];
                }
                case MotionEvent.ACTION_POINTER_UP:{
                    int i = event.getActionIndex();
                        touchPoints[event.getActionIndex()] = null;
                        prevTouchPoints[event.getActionIndex()] = null;

                }
                default:
                    return false;
            }
            for(int i = 0; i < touchPoints.length; i++)
            {
                points.add(touchPoints[i]);
            }
//            points.add(point);
            invalidate();
            return true;
        }
    }
}
