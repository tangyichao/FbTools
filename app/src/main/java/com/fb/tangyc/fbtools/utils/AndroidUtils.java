package com.fb.tangyc.fbtools.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by tangyc on 2015/12/31.
 */
public class AndroidUtils {
    public static void shareMsg(Context context, String activityTitle, String msgTitle, String msgText,
                                String imgPath) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            if (imgPath == null || imgPath.equals("")) {
                intent.setType("text/plain"); // 纯文本
            } else {
                File f = new File(imgPath);
                if (f != null && f.exists() && f.isFile()) {
                    intent.setType("image/png");
                    Uri uri = Uri.fromFile(f);
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                }
            }
            intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
            intent.putExtra(Intent.EXTRA_TEXT, msgText);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(Intent.createChooser(intent, activityTitle));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
