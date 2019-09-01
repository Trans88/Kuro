package com.trans.latte_ec.laucher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.kuro_core.ui.launcher.ScrollLauncherTag;
import com.trans.kuro_core.util.storage.KuroPreference;
import com.trans.kuro_core.util.timer.BaseTimerTask;
import com.trans.kuro_core.util.timer.ITimerListener;
import com.trans.latte_ec.R;
import com.trans.latte_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.ISupportFragment;

import static com.trans.latte_ec.R2.id.start;

public class LaucherDelegate extends KuroDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer=null;

    private Timer mTimer=null;

    private int mCount=5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if (mTimer!=null){
            mTimer.cancel();
            mTimer =null;
            checkIsShowScroll();
        }
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
//    判断是否显示滑动启动页
    private void checkIsShowScroll(){
        if (!KuroPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_TAG.name())){
            getSupportDelegate().start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登录APP
        }
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
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
