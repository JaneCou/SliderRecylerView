package com.sys.blackcat.slider.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangcai on 17/1/13.
 */

public class PullAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pull, parent,false);
      //  LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new ViewHold(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class ViewHold extends RecyclerView.ViewHolder {

        public ViewHold(View itemView) {
            super(itemView);
        }
    }
}
