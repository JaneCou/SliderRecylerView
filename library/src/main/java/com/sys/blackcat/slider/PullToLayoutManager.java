package com.sys.blackcat.slider;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yangcai on 17/1/13.
 */

public class PullToLayoutManager extends LinearLayoutManager {

    private int state = RecyclerView.SCROLL_STATE_IDLE;

    public PullToLayoutManager(Context context) {
        super(context);
    }

    public PullToLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public PullToLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrollDx = super.scrollHorizontallyBy(dx, recycler, state);
        int position = findFirstVisibleItemPosition();
        if (position == 0) {
            return scrollDx - 100;
        }
        return scrollDx;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d("----->", " dy" + dy);
        int scrollDy = super.scrollVerticallyBy(dy, recycler, state);
        if (this.state == RecyclerView.SCROLL_STATE_SETTLING) {
            return scrollDy;
        }
        if (dy > 0) {
            return scrollDy;
        }
        Log.d("----->", " scrollDy" + scrollDy);
        int position = findFirstVisibleItemPosition();
        if (position == 0) {
            View child = recycler.getViewForPosition(position);
            if (getDecoratedLeft(child) == 0) {
                offsetChildrenVertical(-dy);
            }
        } else {
            return scrollDy;
        }
        return scrollDy - Math.abs(dy);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        Class s;
            s.getSuperclass()
        this.state = state;
    }
}
