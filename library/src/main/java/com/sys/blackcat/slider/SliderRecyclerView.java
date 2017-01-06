package com.sys.blackcat.slider;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by yangcai on 17/1/6.
 */

public class SliderRecyclerView extends RecyclerView {
    public SliderRecyclerView(Context context) {
        super(context);
    }

    public SliderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SliderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        Log.d("SliderRecyclerView", "SliderRecyclerView");
        return super.onGenericMotionEvent(event);
    }

    public boolean fling(int velocityX, int velocityY) {
        Log.d("SliderRecyclerView", "fling " + velocityX + " velocityY " + velocityY + " getMinFlingVelocity() "+ getMinFlingVelocity());
        if (Math.abs(velocityX) < getMinFlingVelocity()) {
            return false;
        }


        return false;
    }
}
