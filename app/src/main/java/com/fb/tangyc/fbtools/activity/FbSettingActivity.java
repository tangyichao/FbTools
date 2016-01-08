package com.fb.tangyc.fbtools.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.fb.tangyc.fbtools.BaseActivity;
import com.fb.tangyc.fbtools.R;

/**
 * Created by tangyc on 2016/1/5.
 */
public class FbSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUi();
        initData();
    }

    private void initUi() {

    }

    private void initData() {

    }


}
