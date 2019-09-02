package com.trans.kuro_core.app;

import com.trans.kuro_core.util.storage.KuroPreference;

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }
    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state){
        KuroPreference.setAppFlag(SignTag.SIGN_TAG.name(),true);
    }

    private static boolean isSignIn(){
        return KuroPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
