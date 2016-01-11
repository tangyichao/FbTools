package com.fb.tangyc.fbtools.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class SharedPreferencesUtils {
    private static SharedPreferencesUtils utils = null;
    private static final String CONFIG = "config";
    private static final String FLOATING_WINDOW = "floating_window";
    private static final String FLOATING_RES = "floating_res";
    private static final String FLOATING_AlPHA = "floating_alpha";
    private static final String FLOATING_TOOL_NAME ="floating_tool_name" ;
    private static final String PARAMS_X = "params_x";
    private static final String PARAMS_Y = "params_y";

    private SharedPreferencesUtils() {
    }

    ;

    public static SharedPreferencesUtils getSharedPreferencesUtils() {
        if (utils == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (utils == null) {
                    utils = new SharedPreferencesUtils();
                }
            }
        }
        return utils;
    }

    public void setFloatingRes(Context context, int res) {

        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit().putInt(FLOATING_RES, res).commit();
    }

    public int getFloatingRes(Context context) {
        return context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).getInt(FLOATING_RES, 355);
    }

    public void setFloatingAlpha(Context context, int alpha) {

        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit().putInt(FLOATING_AlPHA, alpha).commit();
    }

    public int getFloatingAlpha(Context context) {
        return context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).getInt(FLOATING_AlPHA, 0);
    }

    public void setFloatingToolName(Context context, String name) {

        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit().putString(FLOATING_TOOL_NAME, name).commit();
    }

    public String getFloatingToolName(Context context) {
        return context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).getString(FLOATING_TOOL_NAME,"");
    }



    public void setFloatingWindow(Context context, boolean isThere) {

        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit().putBoolean(FLOATING_WINDOW, isThere).commit();
    }

    public boolean getFloatingWindow(Context context) {
        return context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).getBoolean(FLOATING_WINDOW, false);
    }

    public void setParamsX(Context context, int x) {

        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit().putInt(PARAMS_X, x).commit();
    }

    public int getParamsX(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();

        return context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).getInt(PARAMS_X, dm.widthPixels);
    }

    public void setParamsY(Context context, int y) {

        context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit().putInt(PARAMS_Y, y).commit();
    }

    public int getParamsY(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        return context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).getInt(PARAMS_Y, dm.heightPixels / 2);
    }


}
