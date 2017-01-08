package com.sys.blackcat.slider.demo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by yangcai on 17-1-7.
 */

public class PathView extends View {
    private Paint redPaint;
    private Path mPath;
    private PathMeasure measure;

    private int currentLength = 0;
    private float point[] = new float[2];
    private int mLenght;

    private int touchX =450;

    private int touchY= 900;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(Color.argb(255, 255, 0, 0));
        redPaint.setStrokeWidth(5);
        redPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();
        mPath.moveTo(100, 100);
        mPath.lineTo(500, 100);
        mPath.rLineTo(250, 500);
        mPath.quadTo(800, 100, 100, 800);
        mPath.cubicTo(100, 200, 300, 400, 500, 600);
        mPath.close();
        measure = new PathMeasure(mPath, false);
        mLenght = (int) measure.getLength();
    }

    private void drawCircle(Canvas canvas, float cX, float cY) {
        Path path = new Path();
        path.addCircle(cX, cY, 10, Path.Direction.CCW);
        redPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, redPaint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        redPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mPath, redPaint);
        //drawCircle(canvas, point[0], point[1]);
        drawll(canvas);
    }


   private void drawll(Canvas canvas ){
       Path path = new Path();
       path.rewind();
       path.moveTo(100,100);
       path.cubicTo(touchX/2, touchY/2, touchX,touchY,1000, 100);
       redPaint.setStyle(Paint.Style.FILL);
       canvas.drawPath(path, redPaint);
   }





    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction()==MotionEvent.ACTION_MOVE){
            touchX = (int) event.getX();
            touchY = (int) event.getY();
            Log.d(" -->","touchX "+touchX);
            invalidate();
        }

        return true;
    }
}
