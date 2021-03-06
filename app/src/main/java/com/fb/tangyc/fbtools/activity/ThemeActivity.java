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
import com.fb.tangyc.fbtools.adapter.ThemeAdapter;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by tangyc on 2016/1/5.
 */
public class ThemeActivity extends BaseActivity {
    private RecyclerView mRvTheme;
    private int[] items = {R.mipmap.ooopic_1, R.mipmap.ooopic_2, R.mipmap.ooopic_3, R.mipmap.ooopic_4, R.mipmap.ooopic_5, R.mipmap.ooopic_6, R.mipmap.ooopic_7, R.mipmap.ooopic_8, R.mipmap.ooopic_9};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        initUi();
        initData();


    }

    private void initUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRvTheme = (RecyclerView) findViewById(R.id.rv_theme);

    }

    private void initData() {
        //mRvTheme.setLayoutManager(new GridLayoutManager(this,2));
        String[] strs = getResources().getStringArray(R.array.theme_name);
        mRvTheme.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        //设置Item增加、移除动画
        mRvTheme.setItemAnimator(new SlideInLeftAnimator());
        mRvTheme.getItemAnimator().setAddDuration(1000);
        mRvTheme.getItemAnimator().setRemoveDuration(1000);
        mRvTheme.getItemAnimator().setMoveDuration(1000);
        mRvTheme.getItemAnimator().setChangeDuration(1000);

        //添加分割线
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        mRvTheme.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mRvTheme.setAdapter(new ThemeAdapter(this, items, strs));

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
