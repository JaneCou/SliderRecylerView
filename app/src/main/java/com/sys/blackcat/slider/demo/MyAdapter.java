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
            R.drawable.a1, R.drawable.a7, R.drawable.a13,
            R.drawable.a2, R.drawable.a8, R.drawable.a14,
            R.drawable.a3, R.drawable.a9, R.drawable.a15,
            R.drawable.a4, R.drawable.a10, R.drawable.a16,
            R.drawable.a5, R.drawable.a11, R.drawable.a17,
            R.drawable.a6, R.drawable.a12, R.drawable.a18
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
