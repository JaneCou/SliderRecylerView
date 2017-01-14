package com.sys.blackcat.slider.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ScaleDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_demo);
    }


    public void onclick(View view){
      ScaleImageView img = (ScaleImageView) findViewById(R.id.scale_img);
        img.yid();
    }
}
