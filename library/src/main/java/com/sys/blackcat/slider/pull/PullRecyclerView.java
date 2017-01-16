package com.sys.blackcat.slider.pull;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangcai on 17-1-16.
 */

public class PullRecyclerView extends RecyclerView {
    public PullRecyclerView(Context context) {
        super(context);
    }

    public PullRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof PullToLayoutManager) {
            PullToLayoutManager pullToLayoutManager = (PullToLayoutManager) layoutManager;
            if (state == RecyclerView.SCROLL_STATE_DRAGGING) {
                if (pullToLayoutManager.state != RecyclerView.SCROLL_STATE_DRAGGING) {
                    View firstView = getChildAt(0);
                    int firstPosition = pullToLayoutManager.getPosition(firstView);
                    int childTop = pullToLayoutManager.getChildTop(firstView);
                    if (firstPosition == 1 && childTop == 0) {
                        pullToLayoutManager.canPullRefresh = true;
                    } else if (firstPosition == 0) {
                        pullToLayoutManager.canPullRefresh = true;
                    } else {
                        pullToLayoutManager.canPullRefresh = false;
                    }
                }
            }
            pullToLayoutManager.state = state;
            if (pullToLayoutManager.state == RecyclerView.SCROLL_STATE_IDLE) {
                View firstView = getChildAt(0);
                int firstPosition = pullToLayoutManager.getPosition(firstView);
                int childTop = pullToLayoutManager.getChildTop(firstView);
                if (firstPosition == 0 && childTop != 0) {
                    smoothScrollBy(0, childTop);
                }
            }
        }
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        scrollToPosition(1);
    }
}
