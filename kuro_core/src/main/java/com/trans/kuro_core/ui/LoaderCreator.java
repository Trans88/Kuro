package com.trans.kuro_core.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;
/**
*@author TRS透明
*Created on 2019/8/14
*function : 以一种缓存的方式存放Loader,不需要在每次使用的时候都进行反射，优化性能。
*/
public final class LoaderCreator  {
    private static final WeakHashMap<String,Indicator> LOADING_MAP =new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type)==null){
            final Indicator indicator=getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }
    private static Indicator getIndicator(String name){
        if (name==null||name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName =new StringBuilder();
        if (!name.contains(".")){
            final String defaultPackageName=AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName).append(".indicators").append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass=Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
