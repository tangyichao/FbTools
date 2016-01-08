package com.fb.tangyc.fbtools.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 *
 */
public class SystemUtil {
    private static final String TAG = "SystemUtil";

    public static int mScreenWidth;
    public static int mScreenHeight;

    public static void initSystem(Context appContext) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) appContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenWidth = displayMetrics.widthPixels;
        mScreenHeight = displayMetrics.heightPixels;

    }

    public static int getStatusBarHeight() {
        int statusBarHeight = Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
        return statusBarHeight;
    }

}
