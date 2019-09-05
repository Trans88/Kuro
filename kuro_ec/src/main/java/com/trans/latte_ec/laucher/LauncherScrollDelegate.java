package com.trans.latte_ec.laucher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.trans.kuro_core.app.AccountManager;
import com.trans.kuro_core.app.IUserChecker;
import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.kuro_core.ui.launcher.ILauncherListener;
import com.trans.kuro_core.ui.launcher.LauncherHolderCreator;
import com.trans.kuro_core.ui.launcher.OnLauncherFinishTag;
import com.trans.kuro_core.ui.launcher.ScrollLauncherTag;
import com.trans.kuro_core.util.storage.KuroPreference;
import com.trans.latte_ec.R;

import java.util.ArrayList;


public class LauncherScrollDelegate extends KuroDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner=null;

    private  final ArrayList<Integer> INTEGERS =new ArrayList<>();
    private ILauncherListener mILauncherListener = null;


    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_scroll_001);
        INTEGERS.add(R.mipmap.launcher_scroll_002);
        INTEGERS.add(R.mipmap.launcher_scroll_003);
        INTEGERS.add(R.mipmap.launcher_scroll_004);
        INTEGERS.add(R.mipmap.launcher_scroll_005);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_fuces})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);

    }
    @Override
    public Object setLayout() {
        mConvenientBanner=new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) context;
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (activity instanceof ILauncherListener) {
//            mILauncherListener = (ILauncherListener) activity;
//        }
//    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        if (position ==INTEGERS.size()-1){
            KuroPreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_TAG.name(),true);
            //检测用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
