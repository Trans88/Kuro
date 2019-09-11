package com.trans.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.trans.kuro_core.delegates.bottom.BottomItemDelegate;
import com.trans.kuro_core.net.RestClient;
import com.trans.kuro_core.net.callback.ISuccess;
import com.trans.kuro_core.ui.recycle.MultipleFildes;
import com.trans.kuro_core.ui.recycle.MultipleItemEntity;
import com.trans.kuro_core.ui.refresh.RefreshHandler;
import com.trans.kuro_core.util.Toast.ToastUtil;
import com.trans.latte_ec.R;
import com.trans.latte_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView=null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout=null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar=null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan=null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView=null;

    private RefreshHandler mRefreshHandler =null;

    private void initRefreshLayout(){
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true,120,300);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRefreshLayout();
        mRefreshHandler=new RefreshHandler(mRefreshLayout);
        ToastUtil.shortShow("hhhh");
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/index_data.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final IndexDataConverter converter=new IndexDataConverter();
                        converter.setJsonData(response);
                        final ArrayList<MultipleItemEntity> list= converter.convert();
                        final String image=list.get(1).getField(MultipleFildes.IMAGE_URL);
//                        ToastUtil.shortShow(image);
                    }
                })
                .build()
                .get();
        mRefreshHandler.firstPage("http://mock.fulingjie.com/mock-android/data/index_data.json");

    }

    @Override
    public void onLazyInitView(Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

//        mRefreshHandler.firstPage("http://mock.fulingjie.com/mock-android/data/index_data.json");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

}
