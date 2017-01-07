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
    public SliderManager(Context context) {
        super(context, HORIZONTAL, false);
    }

    private int maxScrollWidth = 0;
    private static final int MAX_TIME = 1000;

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int will = super.scrollHorizontallyBy(dx, recycler, state);
        Log.d(" ----> ", "will " + will);
        return will;
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

                    /**
                     * {@inheritDoc}
                     */
//                    @Override
//                    protected void onTargetFound(View targetView, RecyclerView.State state, Action action) {
//                        final int dx = calculateDxToMakeVisible(targetView, getHorizontalSnapPreference());
//                        final int dy = calculateDyToMakeVisible(targetView, getVerticalSnapPreference());
//                        final int distance = (int) Math.sqrt(dx * dx + dy * dy);
//                        final int time = calculateTimeForDeceleration(distance);
//                        if (time > 0) {
//                            action.update(-dx, -dy, time, mLinearInterpolator);
//                        }
//                    }

                    @Override
                    protected int calculateTimeForDeceleration(int dx) {
                        Log.d("-----> ", " dx " + dx);

                        super.calculateTimeForDeceleration(dx);

                        if (maxScrollWidth == 0) {
                            maxScrollWidth = getMaxScrollWidth();
                        }

                        int time = dx * MAX_TIME / maxScrollWidth;
                        Log.d("-----> ", " time " + time);
                        return time;
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
}
