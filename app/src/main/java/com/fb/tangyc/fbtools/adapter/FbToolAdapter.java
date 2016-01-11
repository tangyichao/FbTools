package com.fb.tangyc.fbtools.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.utils.SharedPreferencesUtils;

/**
 * Created by tangyc on 2016/1/11.
 */
public class FbToolAdapter extends RecyclerView.Adapter<FbToolAdapter.MyViewHolder> {
    private Context context;
    private String[] itemNames;

    public FbToolAdapter(Context context, String[] itemNames) {
        this.context = context;
        this.itemNames = itemNames;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_tool, null);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mTvName.setText(itemNames[position]);
        String selectName = SharedPreferencesUtils.getSharedPreferencesUtils().getFloatingToolName(context);
        if (selectName.equals(itemNames[position]))
        {
            holder.itemView.setBackgroundColor(Color.RED);
        }else{
            holder.itemView.setBackgroundColor(Color.YELLOW);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.getSharedPreferencesUtils().setFloatingToolName(context, holder.mTvName.getText().toString().trim());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemNames.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;

        public MyViewHolder(View itemView) {

            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
