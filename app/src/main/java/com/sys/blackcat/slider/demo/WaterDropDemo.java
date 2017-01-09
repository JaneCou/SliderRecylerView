package com.sys.blackcat.slider.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sys.blackcat.slider.WaterDropIndicator;

public class WaterDropDemo extends AppCompatActivity {

    private WaterDropIndicator indicator;
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_drop_demo);
        indicator = (WaterDropIndicator) findViewById(R.id.water_drop);
        pager = (ViewPager) findViewById(R.id.vp_page);
        pager.setAdapter(new ViewPagerAdapter());
        indicator.setViewPager(pager);
    }

}
