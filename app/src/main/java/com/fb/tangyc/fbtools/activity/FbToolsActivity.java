package com.fb.tangyc.fbtools.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.fb.tangyc.fbtools.BaseActivity;
import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.adapter.FbToolAdapter;

/**
 * Created by tangyc on 2016/1/5.
 */
public class FbToolsActivity extends BaseActivity {
    private  RecyclerView mRvTools;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_setting);

        initUi();
        initData();
    }
    private void initUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       mRvTools= (RecyclerView) findViewById(R.id.rv_fb_tool);
    }



    private void initData() {
        String[] itemNames=getResources().getStringArray(R.array.tool_name);

        mRvTools.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL));
        //设置Item增加、移除动画
        mRvTools.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        mRvTools.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
         mRvTools.setAdapter(new FbToolAdapter(FbToolsActivity.this,itemNames));


    }
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            //if (parent.getChildPosition(view) != 0) {
            outRect.top = space;
            outRect.left = space;
            outRect.right = space;
            // }

        }
    }

}
