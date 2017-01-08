package com.sys.blackcat.slider.demo;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by yangcai on 17-1-8.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private int data[] = {
            R.mipmap.a1, R.mipmap.a7, R.mipmap.a13,
            R.mipmap.a2, R.mipmap.a8, R.mipmap.a14,
            R.mipmap.a3, R.mipmap.a9, R.mipmap.a15,
//            R.mipmap.a4, R.mipmap.a10, R.mipmap.a16,
//            R.mipmap.a5, R.mipmap.a11, R.mipmap.a17,
//            R.mipmap.a6, R.mipmap.a12, R.mipmap.a18
    };

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        ImageView view = (ImageView) inflater.inflate(R.layout.item, container, false);
        view.setImageResource(data[position]);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
