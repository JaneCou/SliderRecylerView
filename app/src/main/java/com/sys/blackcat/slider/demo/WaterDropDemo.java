package com.sys.blackcat.slider.demo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.sys.blackcat.slider.WaterDropIndicator;

public class WaterDropDemo extends AppCompatActivity {

    private SeekBar seekBar;
    private WaterDropIndicator indicator;
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_drop_demo);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        indicator = (WaterDropIndicator) findViewById(R.id.water_drop);
        pager = (ViewPager) findViewById(R.id.vp_page);
        pager.setAdapter(new ViewPagerAdapter());
        indicator.setViewPager(pager);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("seekBar", " progress" + progress);
                float directionJF= (float) progress/1000;
                Log.d("seekBar", " progress" + directionJF);
                indicator.setDirectionJF(directionJF);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void mergeClick(View view ){
        indicator.startAnimation();
    }
}
