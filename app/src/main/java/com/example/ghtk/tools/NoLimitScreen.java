package com.example.ghtk.tools;

import android.os.Build;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class NoLimitScreen {
    public static void apply(AppCompatActivity activity){
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.R){
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
