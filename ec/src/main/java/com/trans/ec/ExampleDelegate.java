package com.trans.ec;

import android.os.Bundle;
import android.view.View;

import com.trans.kuro_core.delegates.KuroDelegate;

import androidx.annotation.Nullable;

public class ExampleDelegate extends KuroDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegete_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


}
