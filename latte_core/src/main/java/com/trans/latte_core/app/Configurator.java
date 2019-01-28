package com.trans.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.logging.Handler;

/**
 * 进行一些配置文件的存储和获取
 */
public class Configurator {
    private static final WeakHashMap<String,Object> LATTE_CONFIGS=new WeakHashMap<>();
    //存储图标的空间
    private static final ArrayList<IconFontDescriptor>ICONS=new ArrayList<>();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }


    //静态内部类+getInstance 线程安全的懒汉模式写法
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }
    private static class Holder{
        private static final Configurator INSTANCE =new Configurator();
    }

    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }
    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));
            for (int i = 1; i <ICONS.size() ; i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }
    private void checkConfiguration(){
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("configuration is not ready,call configure");
        }
    }
    @SuppressWarnings("unchecked")
    //告诉编译器这个类型是没有检测过得，但是并不抛出警告
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T)LATTE_CONFIGS.get(key.name());
    }
}
