package com.sys.blackcat.slider;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yangcai on 17-1-15.
 */

public abstract class ILoadingLayout extends RecyclerView.ViewHolder implements ILoading {
    protected static final int PULL_TO_REFRESH = 0;
    protected static final int RELEASE_TO_REFRESH = 0;
    protected static final int REFRESHING = 0;
    protected static final int RESET = 0;
    private boolean attachView;


    public ILoadingLayout(View itemView) {
        super(itemView);
    }

    public void move(int dy, int state) {
        if (state == RecyclerView.SCROLL_STATE_DRAGGING) {
        }
    }

    public boolean isAttachView() {
        return attachView;
    }

    public void setAttachView(boolean attachView) {
        this.attachView = attachView;
    }


}
