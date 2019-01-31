package com.trans.ec;
import com.trans.latte_core.activities.ProxyActivity;
import com.trans.latte_core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return null;
    }
}
