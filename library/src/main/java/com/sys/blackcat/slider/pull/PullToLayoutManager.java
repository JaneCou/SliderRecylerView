package com.sys.blackcat.slider.pull;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yangcai on 17/1/13.
 */

public class PullToLayoutManager extends LinearLayoutManager {

    protected int state = RecyclerView.SCROLL_STATE_IDLE;

    protected boolean canPullRefresh;


    public PullToLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
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
        if (this.state == RecyclerView.SCROLL_STATE_SETTLING && dy < 0) {
            int newDy = scrollVerticallyBy(dy);
            if (newDy != 1) {
                return newDy;
            }
        }
        if (this.state == RecyclerView.SCROLL_STATE_DRAGGING && dy < 0) {
            if (canPullRefresh) {
                dy = dy / 2;
                offsetChildrenVertical(-dy);

            } else {
                int newDy = scrollVerticallyBy(dy);
                if (newDy != 1) {
                    return newDy;
                }
            }
        }
        return super.scrollVerticallyBy(dy, recycler, state);

    }

    protected int getChildTop(View child) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        return getDecoratedTop(child) - params.topMargin;
    }

    private int scrollVerticallyBy(int dy) {
        View firstView = getChildAt(0);
        int firstPosition = getPosition(firstView);
        if (firstPosition == 1) {
            int firstTop = getChildTop(firstView);
            if (firstTop - dy > getPaddingTop()) {
                int newDy = firstTop - getPaddingTop();
                if (newDy > 0) {
                    newDy = 0;
                }
                offsetChildrenVertical(-newDy);
                return newDy;
            }
        }
        return 1;
    }

}
