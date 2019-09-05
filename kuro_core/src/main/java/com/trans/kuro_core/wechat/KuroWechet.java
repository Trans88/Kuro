package com.trans.kuro_core.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.trans.kuro_core.app.ConfigKeys;
import com.trans.kuro_core.app.Kuro;
import com.trans.kuro_core.wechat.callback.IWeChetSignInCallBack;

public class KuroWechet {

    public static final String APP_ID =Kuro.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET =Kuro.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);

    private final IWXAPI WXAPI;
    private IWeChetSignInCallBack mSignInCallBack= null;

    private static class Holder{
           private static final KuroWechet INSTANCE=new KuroWechet();
    }

    public static KuroWechet getInstance(){
        return Holder.INSTANCE;
    }

    private KuroWechet(){
        final Activity activity=Kuro.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI=WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public KuroWechet onSignSuccess(IWeChetSignInCallBack callBack){
        this.mSignInCallBack=callBack;
        return this;
    }

    public IWeChetSignInCallBack getSignInCallBack (){
        return mSignInCallBack;
    }

    public final void signIn(){
        final SendAuth.Req req= new SendAuth.Req();
        req.scope ="snsapi_userinfo";
        req.state="random_state";
        WXAPI.sendReq(req);
    }
}
