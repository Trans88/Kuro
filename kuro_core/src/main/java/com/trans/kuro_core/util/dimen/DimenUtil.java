package com.trans.kuro_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.trans.kuro_core.app.Kuro;

/**
*@author TRS透明
*Created on 2019/8/20
*function :测量相关工具
*/
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
