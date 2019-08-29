package com.trans.kuro_core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trans.kuro_core.app.Kuro;


public class KuroPreference {

    private static final SharedPreferences PREFERENCES =PreferenceManager.getDefaultSharedPreferences(Kuro.getApplicationContext());

    private static final String APP_PREFERENCES_KEY="profile";

    private static SharedPreferences getAppPreference(){
        return PREFERENCES;
    }

    public static void setAppProfile(String val){
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY,val)
                .apply();
    }

    public static String getAppProfile(){
        return getAppPreference().getString(APP_PREFERENCES_KEY,null);
    }

    public static JSONObject getAppProfileJson(){
        final String profile =getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile(){
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreference(){
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key,boolean flag){
        getAppPreference()
                .edit()
                .putBoolean(key,flag)
                .apply();
    }

    public static boolean getAppFlag(String key){
        return getAppPreference().getBoolean(key,false);
    }
}
