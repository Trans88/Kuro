package com.trans.kuro_core.net;

import android.content.Context;

import com.trans.kuro_core.net.callback.IError;
import com.trans.kuro_core.net.callback.IFailure;
import com.trans.kuro_core.net.callback.IRequst;
import com.trans.kuro_core.net.callback.ISuccess;
import com.trans.kuro_core.net.callback.RequstCallback;
import com.trans.kuro_core.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author TRS透明
 * Created on 2019/8/13
 * function : 网络框架
 */
public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequst REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url, Map<String, Object> params,
                      IRequst iRequst, ISuccess iSuccess,
                      IFailure iFailure, IError iError, RequestBody body,
    Context context,LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = iRequst;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
        this.BODY = body;
        this.CONTEXT=context;
        this.LOADER_STYLE=loaderStyle;

    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequstStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequstCallback(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR);
    }
    /**
     * 具体使用方法
     */
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
