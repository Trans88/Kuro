package com.trans.kuro_core.wechat.templates;


import com.trans.kuro_core.wechat.BaseWXEntryActivity;
import com.trans.kuro_core.wechat.KuroWechet;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        KuroWechet.getInstance().getSignInCallBack().onSignInSuccess(userInfo);
    }
}
