package com.sys.blackcat.slider.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.sys.blackcat.slider.pull.PullRecyclerView;
import com.sys.blackcat.slider.pull.PullToLayoutManager;

public class PullDemo extends AppCompatActivity {

    private PullRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_demo);
        recyclerView = (PullRecyclerView) findViewById(R.id.activity_loop_demo);
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new PullToLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new PullAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }





}
