package com.trans.ec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.trans.kuro_core.app.Kuro;
import com.trans.kuro_core.net.Interceptors.DebugInterceptor;
import com.trans.latte_ec.database.DatabaseManager;
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
                .withWeChatAppId("")//微信AppId
                .withWeChatAppSecret("")//微信AppSecret
                .configure();
        //初始化数据库
        DatabaseManager.getInstance().init(this);
        //初始化stetho
        initStetho();

    }

    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }
}
