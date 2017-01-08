package com.sys.blackcat.slider.demo;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void loopClick(View view) {
        Intent intent = new Intent(this, LoopDemo.class);
        startActivity(intent);
    }

    public void sliderClick(View view){
        Intent intent = new Intent(this, SlisderDemo.class);
        startActivity(intent);
    }

    public void pathClick(View view){
        Intent intent = new Intent(this, PathDemo.class);
        startActivity(intent);
    }
    public void waterClick(View view){
        Intent intent = new Intent(this, WaterDropDemo.class);
        startActivity(intent);
    }

}
