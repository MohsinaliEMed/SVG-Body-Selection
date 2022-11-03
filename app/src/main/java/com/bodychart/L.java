package com.bodychart;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class L {
    private static final String MY_LOG_TAG = "@***@***@";

    public static void showError(String string) {
            Log.e(MY_LOG_TAG, string);
    }

    public static void showToast(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

}