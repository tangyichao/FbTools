package com.fb.tangyc.fbtools.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.fb.tangyc.fbtools.BaseActivity;
import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.adapter.ThemeAdapter;

/**
 * Created by tangyc on 2016/1/5.
 */
public class ThemeActivity extends BaseActivity {
    private RecyclerView mRvTheme;
    private int[] items = {R.mipmap.ooopic_1, R.mipmap.ooopic_2, R.mipmap.ooopic_3, R.mipmap.ooopic_4, R.mipmap.ooopic_5, R.mipmap.ooopic_6, R.mipmap.ooopic_7, R.mipmap.ooopic_8, R.mipmap.ooopic_9, R.mipmap.ooopic_10, R.mipmap.ooopic_11, R.mipmap.ooopic_1, R.mipmap.ooopic_13, R.mipmap.ooopic_14, R.mipmap.ooopic_15, R.mipmap.ooopic_16, R.mipmap.ooopic_17, R.mipmap.ooopic_18, R.mipmap.ooopic_19, R.mipmap.ooopic_20};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUi();
        initData();

    }

    private void initUi() {
        mRvTheme = (RecyclerView) findViewById(R.id.rv_theme);

    }

    private void initData() {
        //mRvTheme.setLayoutManager(new GridLayoutManager(this,2));
        mRvTheme.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        //设置Item增加、移除动画
        mRvTheme.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        mRvTheme.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mRvTheme.setAdapter(new ThemeAdapter(this, items));

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
                outRect.right=space;
           // }

        }
    }
}
