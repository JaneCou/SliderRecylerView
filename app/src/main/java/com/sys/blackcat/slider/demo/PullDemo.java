package com.sys.blackcat.slider.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sys.blackcat.slider.PullToLayoutManager;

public class PullDemo extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_demo);
        recyclerView = (RecyclerView) findViewById(R.id.activity_loop_demo);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new PullToLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new PullAdapter());
    }





}
