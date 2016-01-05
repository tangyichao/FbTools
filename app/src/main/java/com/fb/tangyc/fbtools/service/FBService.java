package com.fb.tangyc.fbtools.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.fb.tangyc.fbtools.R;

/**
 * Created by tangyc on 2016/1/4.
 */
public class FBService extends Service implements View.OnClickListener, View.OnLongClickListener {
    private WindowManager wm;
    private ViewGroup mLlService;
    private  WindowManager.LayoutParams params ;
    @Override
    public void onCreate() {
//http://ico.58pic.com/
        super.onCreate();

        wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.RIGHT | Gravity.TOP;
        int flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        mLlService = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.lay_service, null);
        mLlService.setOnClickListener(this);
        mLlService.setOnLongClickListener(this);
        mLlService.setVisibility(View.GONE);
        wm.addView(mLlService, params);
    }


    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isShowFB=intent.getBooleanExtra("isShowFB",false);
        mLlService.setVisibility(isShowFB?View.VISIBLE:View.GONE);
        wm.updateViewLayout(mLlService,params);
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
