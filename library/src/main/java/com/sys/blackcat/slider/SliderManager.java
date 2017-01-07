package com.sys.blackcat.slider;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by yangcai on 17/1/6.
 */

public class SliderManager extends LinearLayoutManager {


    public static SliderManager getVertical(Context context){
        return new SliderManager(context,VERTICAL,false);
    }

    public static SliderManager getHorizontal(Context context){
        return new SliderManager(context,HORIZONTAL,false);
    }


    private int maxScrollWidth = 0;
    private int maxScrollheight = 0;
    private static final int MAX_TIME = 500;

    private SliderManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Nullable
                    @Override
                    public PointF computeScrollVectorForPosition(int targetPosition) {
                        if (getChildCount() == 0) {
                            return null;
                        }
                        final int firstChildPos = getPosition(getChildAt(0));
                        final int direction = targetPosition < firstChildPos ? -1 : 1;
                        return new PointF(direction, 0);
                    }
                    @Override
                    protected int calculateTimeForDeceleration(int dx) {
                        super.calculateTimeForDeceleration(dx);
                        if (canScrollHorizontally()){
                            if (maxScrollWidth == 0) {
                                maxScrollWidth = getMaxScrollWidth();
                            }
                            int time = dx * MAX_TIME / maxScrollWidth;
                            return time;
                        }else {
                            if (maxScrollheight == 0) {
                                maxScrollheight = getMaxScrollHeight();
                            }
                            int time = dx * MAX_TIME / maxScrollheight;
                            return time;
                        }
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }


    private int getMaxScrollWidth() {
        try {
            Field field = RecyclerView.LayoutManager.class.getDeclaredField("mRecyclerView");
            field.setAccessible(true);
            return ((RecyclerView) field.get(this)).getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getMaxScrollHeight() {
        try {
            Field field = RecyclerView.LayoutManager.class.getDeclaredField("mRecyclerView");
            field.setAccessible(true);
            return ((RecyclerView) field.get(this)).getMeasuredHeight() - getPaddingBottom() - getPaddingTop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
