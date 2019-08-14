package com.trans.kuro_core.net;

import android.content.Context;

import com.trans.kuro_core.net.callback.IError;
import com.trans.kuro_core.net.callback.IFailure;
import com.trans.kuro_core.net.callback.IRequst;
import com.trans.kuro_core.net.callback.ISuccess;
import com.trans.kuro_core.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {
    private  String mUrl=null;
    private  static final Map<String,Object> PARAMS=RestCreator.getParams();
    private  IRequst mIRequst=null;
    private  ISuccess mISuccess=null;
    private  IFailure mIFailure=null;
    private  IError mIError=null;
    private  RequestBody mRequestBody=null;
    private Context mContext=null;
    private LoaderStyle mLoaderStyle=null;

    RestClientBuilder(){

    }

    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }
    public final RestClientBuilder parmas(WeakHashMap<String ,Object> params){
        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuilder parmas(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mRequestBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuilder onRequst(IRequst iRequst){
        this.mIRequst=iRequst;
        return this;
    }
    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return this;
    }
    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure=iFailure;
        return this;
    }
    public final RestClientBuilder errer(IError iError){
        this.mIError=iError;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }
    public final RestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallClipRotateIndicator;
        return this;
    }
    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequst,mISuccess,mIFailure,mIError,mRequestBody,mContext,mLoaderStyle);
    }
}
