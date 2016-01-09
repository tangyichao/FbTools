package com.fb.tangyc.fbtools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;

import com.fb.tangyc.fbtools.BaseActivity;
import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.utils.SharedPreferencesUtils;

/**
 * Created by tangyc on 2016/1/5.
 */
public class FbSettingActivity extends BaseActivity {
    private RadioGroup mRgAlpha;
    public static final String ACTION_ALPHA = "com.fb.alpha";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_setting);

        initUi();
        initData();
    }

    private void initUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRgAlpha = (RadioGroup) findViewById(R.id.rg_alpha);
    }

    private void initData() {
        int alpha = SharedPreferencesUtils.getSharedPreferencesUtils().getFloatingAlpha(this);
        if (alpha == 0) {
            mRgAlpha.check(R.id.rb_not_alpha);
        } else {
            mRgAlpha.check(R.id.rb_half_alpha);
        }
        mRgAlpha.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int check = 0xFF;
                switch (checkedId) {
                    case R.id.rb_not_alpha:
                        check = 0xFF;
                        break;
                    case R.id.rb_half_alpha:
                        check = 0xFF/2;
                        break;
                }
                Intent intent = new Intent();
                intent.setAction(ACTION_ALPHA);
                sendBroadcast(intent);
                SharedPreferencesUtils.getSharedPreferencesUtils().setFloatingAlpha(FbSettingActivity.this, check);
            }
        });

    }


}
