package com.fb.tangyc.fbtools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.fb.tangyc.fbtools.BaseActivity;
import com.fb.tangyc.fbtools.R;

/**
 * Created by tangyc on 2016/1/8.
 */
public class SplashActivity extends BaseActivity {
    private static final int LOADING_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, LOADING_TIME);

    }
}
