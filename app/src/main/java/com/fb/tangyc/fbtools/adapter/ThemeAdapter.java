package com.fb.tangyc.fbtools.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.service.FBService;
import com.fb.tangyc.fbtools.utils.SharedPreferencesUtils;

/**
 * Created by tangyc on 2016/1/7.
 */
public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.MyViewHolder> {

    private Context context;
    private int[] items;
    private String[] strs;
    public ThemeAdapter(Context context, int[] items, String[] strs) {
        this.context = context;
        this.items = items;
        this.strs=strs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LinearLayout.inflate(context, R.layout.item_theme, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mIvTheme.setImageResource(items[position]);
        holder.mTvName.setText(strs[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.getSharedPreferencesUtils().setFloatingRes(context,items[position]);
                Intent intent = new Intent(context, FBService.class);
                intent.putExtra("isShowFB", true);
                context.startService(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvTheme;
        TextView mTvName;
        public MyViewHolder(View itemView) {

            super(itemView);
            mIvTheme = (ImageView) itemView.findViewById(R.id.iv_theme);
            mTvName= (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
