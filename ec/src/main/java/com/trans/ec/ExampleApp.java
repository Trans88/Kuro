package com.trans.ec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.trans.kuro_core.app.Kuro;
import com.trans.kuro_core.net.Interceptors.DebugInterceptor;
import com.trans.latte_ec.icon.FontEcModule;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Kuro.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())//自定义的图标库
                .withLoaderDelayed(1000)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();

    }
}
