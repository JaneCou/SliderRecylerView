package com.sys.blackcat.slider;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

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
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof SliderManager) {
                SliderManager linearLayoutManager = (SliderManager) layoutManager;
                int firstItme = linearLayoutManager.findFirstVisibleItemPosition();
                View childView = layoutManager.findViewByPosition(firstItme);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
                if (linearLayoutManager.canScrollHorizontally()) {
                    int end = linearLayoutManager.getDecoratedRight(childView) + params.rightMargin;
                    int width = getMeasuredWidth();
                    if (end == 0 || end == width) {
                        return;
                    }
                    if (end >= width / 2) {
                      //  smoothScrollToPosition(firstItme);
                        smoothScrollBy(-(getMeasuredWidth() - end), 0);
                    } else {
                        smoothScrollBy(end, 0);
                       // smoothScrollToPosition(firstItme + 1);
                    }
                } else {
                    int bottom = linearLayoutManager.getDecoratedBottom(childView) + params.bottomMargin;
                    int height = getMeasuredHeight();
                    if (bottom == 0 || bottom == height) {
                        return;
                    }
                    if (bottom > height / 2) {
                        smoothScrollBy(0, -(getMeasuredHeight() - bottom));
                      //  smoothScrollToPosition(firstItme);
                    } else {
                        smoothScrollBy(0, bottom);
                      //  smoothScrollToPosition(firstItme + 1);
                    }
                }
            }
        }
    }

    public boolean fling(int velocityX, int velocityY) {
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager.canScrollHorizontally() && Math.abs(velocityX) < getMinFlingVelocity()) {
            return false;
        }
        if (layoutManager.canScrollVertically() && Math.abs(velocityY) < getMinFlingVelocity()) {
            return false;
        }
        if (layoutManager instanceof SliderManager) {
            SliderManager linearLayoutManager = (SliderManager) layoutManager;
            int firstItme = linearLayoutManager.findFirstVisibleItemPosition();
            View childView = layoutManager.findViewByPosition(firstItme);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            if (linearLayoutManager.canScrollHorizontally()) {
                int end = linearLayoutManager.getDecoratedRight(childView) + params.rightMargin;
                if (velocityX > 0) {//从右往左滑动
                    if (firstItme == getAdapter().getItemCount() - 1) {
                        return false;
                    }
                    smoothScrollBy(end, 0);
                    // smoothScrollToPosition(firstItme + 1);
                } else {//从左往右滑动
                    smoothScrollBy(-(getMeasuredWidth() - end), 0);
                    // smoothScrollToPosition(firstItme);
                }
            } else {
                int bottom = linearLayoutManager.getDecoratedBottom(childView) + params.bottomMargin;
                if (velocityY > 0) {//从上往下滑动
                    if (firstItme == getAdapter().getItemCount() - 1) {
                        return false;
                    }
                    smoothScrollBy(0, bottom);
                    //  smoothScrollToPosition(firstItme + 1);
                } else {//从下往上滑动
                    smoothScrollBy(0, -(getMeasuredHeight() - bottom));
                }
            }
            return true;
        }
        return super.fling(velocityX, velocityY);
    }
}
