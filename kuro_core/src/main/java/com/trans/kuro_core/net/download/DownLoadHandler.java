package com.trans.kuro_core.net.download;

import android.os.AsyncTask;

import com.trans.kuro_core.net.RestCreator;
import com.trans.kuro_core.net.callback.IError;
import com.trans.kuro_core.net.callback.IFailure;
import com.trans.kuro_core.net.callback.IRequst;
import com.trans.kuro_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownLoadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequst REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IError ERROR;

    public DownLoadHandler(String url, IRequst REQUEST, ISuccess SUCCESS, IFailure FAILURE, String DOWNLOAD_DIR, String EXTENSION, String NAME, IError ERROR) {
        this.URL = url;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.ERROR = ERROR;
    }

    public final void handleDownload(){
        if (REQUEST!=null){
            REQUEST.onRequstStart();
        }
        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            final ResponseBody responseBody=response.body();

                            final SaveFileTask tesk=new SaveFileTask(REQUEST,SUCCESS);

                            tesk.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,response,NAME);

                            //判断文件是否下载完全
                            if (tesk.isCancelled()){
                                if (REQUEST!=null){
                                    REQUEST.onRequstEnd();
                                }
                            }
                        }else {
                            if (ERROR!=null){
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
