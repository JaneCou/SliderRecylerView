package com.sys.blackcat.slider;

/**
 * Created by yangcai on 17-1-15.
 */

public interface ILoading {

    void pulltoRefresh();

    abstract void releasetoRefresh();

    abstract void refreshing();

    abstract void rest();
}
