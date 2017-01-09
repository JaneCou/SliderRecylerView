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
            R.drawable.a1, R.drawable.a7, R.drawable.a13,
            R.drawable.a2, R.drawable.a8, R.drawable.a14,
            R.drawable.a3, R.drawable.a9, R.drawable.a15,
//            R.drawable.a4, R.drawable.a10, R.drawable.a16,
//            R.drawable.a5, R.drawable.a11, R.drawable.a17,
//            R.drawable.a6, R.drawable.a12, R.drawable.a18
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
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
