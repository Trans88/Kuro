package com.trans.ec;
import com.trans.kuro_core.activities.ProxyActivity;
import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.latte_ec.laucher.LaucherDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public KuroDelegate setRootDelegate() {
        return new LaucherDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
