package com.trans.kuro_core.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 进行一些配置文件的存储和获取
 */
public class Configurator {
    private static final HashMap<Object,Object> KURO_CONFIGS =new HashMap<>();
    //存储图标的空间
    private static final ArrayList<IconFontDescriptor>ICONS=new ArrayList<>();
    //拦截器
    private static final ArrayList<Interceptor>INTERCEPTORS=new ArrayList<>();

    private static final Handler HANDLER = new Handler();

    private Configurator(){
        KURO_CONFIGS.put(ConfigKeys.CONFIG_READY,false);
        KURO_CONFIGS.put(ConfigKeys.HANDLER,HANDLER);
    }


    //静态内部类+getInstance 线程安全的懒汉模式写法
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }


    private static class Holder{
        private static final Configurator INSTANCE =new Configurator();
    }

    final HashMap<Object,Object> getKuroConfigs(){
        return KURO_CONFIGS;
    }
    public final void configure(){
        initIcons();
        KURO_CONFIGS.put(ConfigKeys.CONFIG_READY,true);
    }
    public final Configurator withApiHost(String host){
        KURO_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed){
        KURO_CONFIGS.put(ConfigKeys.LOADER_DELAYED,delayed);
        return this;
    }
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        KURO_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        KURO_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Configurator withWeChatAppId(String appId){
        KURO_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID,appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String secret){
        KURO_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET,secret);
        return this;
    }

    public final Configurator withActivity(Activity activity){
        KURO_CONFIGS.put(ConfigKeys.ACTIVITY,activity);
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
        final boolean isReady= (boolean) KURO_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if(!isReady){
            throw new RuntimeException("configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    //告诉编译器这个类型是没有检测过得，但是并不抛出警告
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        final Object value =KURO_CONFIGS.get(key);
        if (value ==null){
            throw new NullPointerException(key.toString()+"is NULL");
        }
        return (T) KURO_CONFIGS.get(key);
    }
}
