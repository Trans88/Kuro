package com.trans.kuro_core.net.Interceptors;


import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseInterceptor implements Interceptor {

    /**
     * get请求获取参数,使用LinkedHashMap是因为需要有序的键值对
     * @param chain Okhttp3中的一个接口
     * @return
     */
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl url=chain.request().url();
        int size=url.querySize();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        for (int i = 0; i <size ; i++) {
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameters(Chain chain,String key){
        final Request request=chain.request();
        return  request.url().queryParameter(key);
    }

    /**
     * post请求参数
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        final FormBody formBody= (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        int size=formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i),formBody.value(i));
        }
        return params;
    }

    protected String getBodyParameters(Chain chain,String key){
        return  getBodyParameters(chain).get(key);
    }

}
