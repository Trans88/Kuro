package com.trans.ec;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.trans.kuro_core.activities.ProxyActivity;
import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.latte_ec.laucher.LaucherDelegate;
import com.trans.latte_ec.laucher.LauncherScrollDelegate;
import com.trans.latte_ec.sign.SignInDelegate;
import com.trans.latte_ec.sign.SignUpDelegate;


public class ExampleActivity extends ProxyActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar =getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public KuroDelegate setRootDelegate() {
        return new SignInDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
