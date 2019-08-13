package com.trans.ec;
import com.trans.kuro_core.activities.ProxyActivity;
import com.trans.kuro_core.delegates.KuroDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public KuroDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
