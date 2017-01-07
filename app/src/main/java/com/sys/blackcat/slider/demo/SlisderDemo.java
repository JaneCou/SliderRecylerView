package com.sys.blackcat.slider.demo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sys.blackcat.slider.SliderManager;

public class SlisderDemo extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_demo);
        setTitle("horizontal");
        recyclerView = (RecyclerView) findViewById(R.id.activity_loop_demo);
        SliderManager layoutManager = SliderManager.getHorizontal(this);
        recyclerView.setLayoutManager(layoutManager);
      //  recyclerView.setLayoutManager(new SliderLayoutManager());
        recyclerView.setAdapter(new MyAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_vertical:
               if ( !getTitle().equals("vertical")){
                   recyclerView.setLayoutManager(SliderManager.getVertical(this));
                   setTitle("vertical");
               }

                return true;
            case R.id.menu_Horizontal:
                if ( !getTitle().equals("horizontal")){
                    recyclerView.setLayoutManager(SliderManager.getHorizontal(this));
                    setTitle("horizontal");
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
