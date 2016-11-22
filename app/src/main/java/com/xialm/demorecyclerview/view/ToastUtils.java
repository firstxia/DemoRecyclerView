package com.xialm.demorecyclerview.view;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Xialm on 2016/11/22.
 */

public class ToastUtils {

    private static Toast toast;

    public static void ShowToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
