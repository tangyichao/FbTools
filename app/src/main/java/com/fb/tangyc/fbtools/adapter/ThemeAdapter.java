package com.fb.tangyc.fbtools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fb.tangyc.fbtools.R;

/**
 * Created by tangyc on 2016/1/7.
 */
public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.MyViewHolder> {

    private Context context;
    private int[] items;

    public ThemeAdapter(Context context, int[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LinearLayout.inflate(context, R.layout.item_theme, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mIvTheme.setImageResource(items[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return items.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvTheme;

        public MyViewHolder(View itemView) {

            super(itemView);
            mIvTheme = (ImageView) itemView.findViewById(R.id.iv_theme);
        }
    }
}
