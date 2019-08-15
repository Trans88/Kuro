package com.trans.kuro_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.trans.kuro_core.app.Kuro;

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources=Kuro.getApplication().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources=Kuro.getApplication().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
