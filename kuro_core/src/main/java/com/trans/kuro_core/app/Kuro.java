package com.trans.kuro_core.app;

import android.content.Context;
import java.util.HashMap;

public final class Kuro {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static HashMap<String ,Object> getConfigurations(){
        return Configurator.getInstance().getKuroConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
