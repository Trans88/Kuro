package com.trans.kuro_core.app;

import android.content.Context;
import java.util.HashMap;

public final class Kuro {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static HashMap<Object ,Object> getConfigurations(){
        return Configurator.getInstance().getKuroConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }
}
