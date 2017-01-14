package com.sys.blackcat.slider.demo;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 * Created by yangcai on 17/1/10.
 */

public class ScaleImageView extends ImageView {
    private ScaleGestureDetector gestureDetector;
    private Matrix matrix ;
    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.MATRIX);
        matrix = getImageMatrix();
        gestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                Log.d(" ---> ", " detector "+ detector.getScaleFactor());
                matrix.postScale(detector.getScaleFactor(),detector.getScaleFactor(),detector.getFocusX(),detector.getFocusY());
                setImageMatrix(matrix);
             //  invalidate();
                return true;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                Log.d(" ---> ", " detector onScaleBegin" );
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
                Log.d(" ---> ", " detector onScaleEnd");

            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }


    public void yid(){
        matrix.postTranslate(100,100);
        setImageMatrix(matrix);
    }
}
