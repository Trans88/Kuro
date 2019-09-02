package com.trans.latte_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trans.kuro_core.app.AccountManager;
import com.trans.latte_ec.database.DatabaseManager;
import com.trans.latte_ec.database.UserProfile;


public class SignHandler {

    public static void onSignIn(String response ,ISignListener signListener){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");

        final long userId=profileJson.getLong("userId");
        final String name=profileJson.getString("name");
        final String avatar=profileJson.getString("avatar");
        final String gender=profileJson.getString("gender");
        final String address=profileJson.getString("address");

        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);
//        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
    public static void onSignUp(String response ,ISignListener signListener){
        final JSONObject profileJson= JSON.parseObject(response).getJSONObject("data");

        final long userId=profileJson.getLong("userId");
        final String name=profileJson.getString("name");
        final String avatar=profileJson.getString("avatar");
        final String gender=profileJson.getString("gender");
        final String address=profileJson.getString("address");

        final UserProfile profile=new UserProfile(userId,name,avatar,gender,address);
//        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
