package com.sys.blackcat.slider.demo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by yangcai on 17/1/6.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private int data[] = {
            R.mipmap.a1, R.mipmap.a7, R.mipmap.a13,
            R.mipmap.a2, R.mipmap.a8, R.mipmap.a14,
            R.mipmap.a3, R.mipmap.a9, R.mipmap.a15,
            R.mipmap.a4, R.mipmap.a10, R.mipmap.a16,
            R.mipmap.a5, R.mipmap.a11, R.mipmap.a17,
            R.mipmap.a6, R.mipmap.a12, R.mipmap.a18
    };

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        Log.d("==MyAdapter>", "onCreateViewHolder " );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.itemView instanceof ImageView) {
            ((ImageView) holder.itemView).setImageResource(data[position]);
            ((ImageView) holder.itemView).setScaleType(ImageView.ScaleType.FIT_XY);
        }
//            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//            lp.width = RecyclerView.LayoutParams.;
//            lp.height = RecyclerView.LayoutParams.WRAP_CONTENT;
//            holder.itemView.setLayoutParams(lp);
        Log.d("==MyAdapter>", "onBindViewHolder " );
    }

    @Override
    public int getItemCount() {
        return 18;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
