package com.sys.blackcat.slider;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yangcai on 17/1/13.
 */

public class PullToLayoutManager extends LinearLayoutManager {

    private int state = RecyclerView.SCROLL_STATE_IDLE;

    private boolean canPullRefresh;


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

            } else {
                int newDy = scrollVerticallyBy(dy);
                if (newDy != 1) {
                    return newDy;
                }
            }
            // Log.d(" --- > ", " firstPosition " + firstPosition);
        }
        return super.scrollVerticallyBy(dy, recycler, state);

    }

    private int getChildTop(View child) {
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


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_DRAGGING) {
            if (this.state != RecyclerView.SCROLL_STATE_DRAGGING) {
                View firstView = getChildAt(0);
                int firstPosition = getPosition(firstView);
                int childTop = getChildTop(firstView);
                if (firstPosition == 1 && childTop == 0) {
                    canPullRefresh = true;
                } else {
                    canPullRefresh = false;
                }
            }
        }
        this.state = state;
    }


}
