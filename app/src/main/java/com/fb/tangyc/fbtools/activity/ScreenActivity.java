package com.fb.tangyc.fbtools.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.fb.tangyc.fbtools.BaseActivity;
import com.fb.tangyc.fbtools.Contants;
import com.fb.tangyc.fbtools.R;

/**
 * Created by tyc on 16/1/11.
 */
public class ScreenActivity  extends BaseActivity{
    private static float screen=0f;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

    }

    @Override
    public void onResume() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        screen=lp.screenBrightness;
        lp.screenBrightness=1f;
        getWindow().setAttributes(lp);
        super.onResume();
    }

    @Override
    public void onPause() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness=screen;
        getWindow().setAttributes(lp);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Contants.Screenlight.type=false;
    }
}
