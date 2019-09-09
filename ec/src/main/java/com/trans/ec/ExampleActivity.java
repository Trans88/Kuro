package com.trans.ec;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.trans.kuro_core.activities.ProxyActivity;
import com.trans.kuro_core.app.Kuro;
import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.kuro_core.ui.launcher.ILauncherListener;
import com.trans.kuro_core.ui.launcher.OnLauncherFinishTag;
import com.trans.kuro_core.util.Toast.ToastUtil;
import com.trans.latte_ec.laucher.LaucherDelegate;
import com.trans.latte_ec.laucher.LauncherScrollDelegate;
import com.trans.latte_ec.main.EcBottomDelegate;
import com.trans.latte_ec.sign.ISignListener;
import com.trans.latte_ec.sign.SignInDelegate;
import com.trans.latte_ec.sign.SignUpDelegate;


public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar =getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Kuro.getConfiguretor().withActivity(this);
    }

    @Override
    public KuroDelegate setRootDelegate() {
        return new LaucherDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onSignInSuccess() {
        ToastUtil.shortShow("登录成功");
    }

    @Override
    public void onSignUpSuccess() {
        ToastUtil.shortShow("注册成功");
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                ToastUtil.shortShow("启动结束，用户登录了");
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                ToastUtil.shortShow("启动结束，用户没登录");
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
        }
    }
}
