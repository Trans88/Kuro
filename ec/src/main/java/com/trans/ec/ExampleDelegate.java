package com.trans.ec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.trans.latte_core.delegates.LatteDelegate;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegete_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
