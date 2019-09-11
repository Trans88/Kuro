package com.trans.kuro_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.trans.kuro_core.app.Kuro;
import com.trans.kuro_core.net.RestClient;
import com.trans.kuro_core.net.callback.ISuccess;
import com.trans.kuro_core.util.Toast.ToastUtil;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout){
        REFRESH_LAYOUT=swipeRefreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Kuro.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }

    public void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
