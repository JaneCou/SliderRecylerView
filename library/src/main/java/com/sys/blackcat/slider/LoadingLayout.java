package com.sys.blackcat.slider;

import android.view.View;
import android.widget.TextView;

/**
 * Created by yangcai on 17-1-14.
 */

public class LoadingLayout extends ILoadingLayout {

    private TextView pullMsg;

    public LoadingLayout(View itemView) {
        super(itemView);
        pullMsg = (TextView) itemView.findViewById(R.id.pull_msg);
    }
    @Override
    public void pulltoRefresh() {
        pullMsg.setText(R.string.pull_down_refresh);
    }

    @Override
    public void releasetoRefresh() {
        pullMsg.setText(R.string.release_to_refresh);
    }

    @Override
    public void refreshing() {
        pullMsg.setText(R.string.refreshing);
    }

    @Override
    public void rest() {
        pullMsg.setText(R.string.refresh_done);
    }
}
