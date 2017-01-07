package com.sys.blackcat.slider;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
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
                        smoothScrollToPosition(firstItme);
                    } else {
                        smoothScrollToPosition(firstItme + 1);
                    }
                } else {
                    int bottom = linearLayoutManager.getDecoratedBottom(childView) + params.bottomMargin;
                    int height = getMeasuredHeight();
                    if (bottom == 0 || bottom == height) {
                        return;
                    }
                    if (bottom > height / 2) {
                        smoothScrollToPosition(firstItme);
                    } else {
                        smoothScrollToPosition(firstItme + 1);
                    }
                }
            }
        }
    }

    public boolean fling(int velocityX, int velocityY) {
        Log.d("---->", "velocityY " + velocityY);
        Log.d("---->", "velocityX " + velocityX);
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager.canScrollHorizontally() && Math.abs(velocityX) < getMinFlingVelocity() * 2) {
            return false;
        }
        if (layoutManager.canScrollVertically() && Math.abs(velocityY) < getMinFlingVelocity()) {
            return false;
        }
        if (layoutManager instanceof SliderManager) {
            SliderManager linearLayoutManager = (SliderManager) layoutManager;
            int firstItme = linearLayoutManager.findFirstVisibleItemPosition();
            if (linearLayoutManager.canScrollHorizontally()) {
                if (velocityX > 0) {//从右往左滑动
                    if (firstItme == getAdapter().getItemCount() - 1) {
                        return false;
                    }
                    smoothScrollToPosition(firstItme + 1);
                } else {//从左往右滑动
                    smoothScrollToPosition(firstItme);
                }
            } else {
                if (velocityY > 0) {//从上往下滑动
                    if (firstItme == getAdapter().getItemCount() - 1) {
                        return false;
                    }
                    smoothScrollToPosition(firstItme + 1);
                } else {//从下往上滑动
                    smoothScrollToPosition(firstItme);
                }
            }
            return true;
        }
        return super.fling(velocityX, velocityY);
    }
}
