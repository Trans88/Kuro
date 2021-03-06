package com.trans.ec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.kuro_core.net.RestClient;
import com.trans.kuro_core.net.callback.IError;
import com.trans.kuro_core.net.callback.IFailure;
import com.trans.kuro_core.net.callback.ISuccess;


public class ExampleDelegate extends KuroDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegete_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }
    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("HAHAHAHA", response);
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .errer(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })

                .build().get();

    }

}
