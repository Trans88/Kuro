package com.trans.kuro_core.net.callback;

import android.os.Handler;

import com.trans.kuro_core.app.ConfigKeys;
import com.trans.kuro_core.app.Kuro;
import com.trans.kuro_core.ui.loader.KuroLoader;
import com.trans.kuro_core.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequstCallback implements Callback<String> {
    private final IRequst REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;

    private static final Handler HANDLER=new Handler();

    public RequstCallback(IRequst requst, ISuccess success, IFailure failure, IError error,LoaderStyle loaderStyle) {
        this.REQUEST = requst;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE=loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure();
        }

        if (REQUEST!=null){
            REQUEST.onRequstEnd();
        }
        stopLoading();
    }

    private void stopLoading(){
        final long delayed= (long) Kuro.getConfigurations().get(ConfigKeys.LOADER_DELAYED);
        if (LOADER_STYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    KuroLoader.stopLoading();
                }
            },delayed);
        }
    }
}
