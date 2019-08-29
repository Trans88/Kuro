package com.trans.latte_ec.laucher;

import android.os.Bundle;
import android.view.View;

import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.kuro_core.util.timer.BaseTimerTask;
import com.trans.kuro_core.util.timer.ITimerListener;
import com.trans.latte_ec.R;
import com.trans.latte_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.OnClick;

public class LaucherDelegate extends KuroDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer=null;

    private Timer mTimer=null;

    private int mCount=5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){

    }

    private void initTimer(){
        mTimer=new Timer();
        final BaseTimerTask task=new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }
    @Override
    public Object setLayout() {
        return R.layout.delegete_laucher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer!=null){
                            mTimer.cancel();
                            mTimer =null;
                        }
                    }
                }
            }
        });
    }
}
