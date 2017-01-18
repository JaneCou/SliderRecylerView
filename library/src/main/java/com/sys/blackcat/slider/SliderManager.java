package com.sys.blackcat.slider;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by yangcai on 17/1/6.
 */

public class SliderManager extends LinearLayoutManager {


    public static SliderManager getVertical(Context context){
        return new SliderManager(context,VERTICAL,false);
    }

    public static SliderManager getHorizontal(Context context){
        return new SliderManager(context,HORIZONTAL,false);
    }
    private SliderManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }
}
