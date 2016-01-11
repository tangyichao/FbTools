package com.fb.tangyc.fbtools.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.fb.tangyc.fbtools.Contants;
import com.fb.tangyc.fbtools.R;
import com.fb.tangyc.fbtools.activity.ScreenActivity;
import com.fb.tangyc.fbtools.utils.FlashLightUtils;
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
    public static final String ACTION_ALPHA = "com.fb.alpha";
    private ServiceReceiver receiver;

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
        int alpha = SharedPreferencesUtils.getSharedPreferencesUtils().getFloatingAlpha(this);
        windowView.findViewById(R.id.iv_icon).getBackground().setAlpha(alpha);
        wManager.addView(windowView, mParams);// 添加窗口
        windowView.findViewById(R.id.iv_icon).setOnClickListener(this);
        initData();
    }

    private void initData() {
        receiver = new ServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_ALPHA);
        filter.setPriority(2147483647);
        registerReceiver(receiver, filter);
    }


    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isShowFB = intent.getBooleanExtra("isShowFB", false);

        int res = SharedPreferencesUtils.getSharedPreferencesUtils().getFloatingRes(this);
        if (res != 0) {
            windowView.findViewById(R.id.iv_icon).setBackgroundResource(res);
        }
        windowView.setVisibility(isShowFB ? View.VISIBLE : View.GONE);
        wManager.updateViewLayout(windowView, mParams);
        return super.onStartCommand(intent, flags, startId);

    }


    class ServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                if (action.equals(ACTION_ALPHA)) {
                    int alpha = SharedPreferencesUtils.getSharedPreferencesUtils().getFloatingAlpha(context);
                    windowView.findViewById(R.id.iv_icon).getBackground().setAlpha(alpha);
                    windowView.invalidate();
                }
            }

        }
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
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        String toolType = SharedPreferencesUtils.getSharedPreferencesUtils().getFloatingToolName(this);
        switch (v.getId()) {
            case R.id.iv_icon:
                //手电

                if (Contants.Splashlight.splashlight.equals(toolType)) {
                    if (Contants.Splashlight.type == false) {
                        try {
                            FlashLightUtils.openFlash(this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Contants.Splashlight.type = true;
                    } else {

                        try {
                            FlashLightUtils.closeFlash();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Contants.Splashlight.type = false;
                    }
                }else if(Contants.Screenlight.screenlight.equals(toolType)){
                    if(Contants.Screenlight.type == false)
                    {
                        Intent intent =new Intent();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setClass(this, ScreenActivity.class);
                        startActivity(intent);
                        Contants.Screenlight.type=true;
                    }

                }
                break;

        }
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
