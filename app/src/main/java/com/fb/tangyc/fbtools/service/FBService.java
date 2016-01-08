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
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.utils.SharedPreferencesUtils;
import com.fb.tangyc.fbtools.view.FloatButtonLayout;

/**
 * Created by tangyc on 2016/1/4.
 */
public class FBService extends Service implements View.OnClickListener, View.OnLongClickListener, SurfaceHolder.Callback {

    private WindowManager wManager;// 窗口管理者
    private WindowManager.LayoutParams mParams;// 窗口的属性

    private FloatButtonLayout windowView;
    private SurfaceHolder holder;

    @Override
    public void onCreate() {
//http://ico.58pic.com/
        super.onCreate();
        wManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;// 系统提示window
        mParams.format = PixelFormat.TRANSLUCENT;// 支持透明
        // mParams.format = PixelFormat.RGBA_8888;
        mParams.flags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;// 焦点
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;// 窗口的宽和高
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
        mParams.y = SharedPreferencesUtils.getSharedPreferencesUtils().getParamsY(getApplicationContext());
        mParams.x = SharedPreferencesUtils.getSharedPreferencesUtils().getParamsX(getApplicationContext());
        mParams.windowAnimations = android.R.style.Animation_Toast;
        // mParams.alpha = 0.8f;//窗口的透明度

        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        windowView = (FloatButtonLayout) layoutInflater.inflate(R.layout.float_button_layout, null);
        // ivFlashLight = (ImageView)
        // windowView.findViewById(R.id.iv_flashlight);
        SurfaceView localSurfaceView = (SurfaceView) windowView.findViewById(R.id.sfPreview);
        this.holder = localSurfaceView.getHolder();
        this.holder.addCallback(this);
        windowView.setVisibility(View.VISIBLE);
        windowView.findViewById(R.id.iv_icon).setBackgroundResource(R.mipmap.ooopic_1);
        wManager.addView(windowView, mParams);// 添加窗口
    }


    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isShowFB = intent.getBooleanExtra("isShowFB", false);
        windowView .setVisibility(isShowFB ? View.VISIBLE : View.GONE);
        wManager.updateViewLayout( windowView, mParams);
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
