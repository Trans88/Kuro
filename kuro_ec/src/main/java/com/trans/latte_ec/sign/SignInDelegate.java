package com.trans.latte_ec.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.kuro_core.net.RestClient;
import com.trans.kuro_core.net.callback.ISuccess;
import com.trans.kuro_core.ui.launcher.ILauncherListener;
import com.trans.kuro_core.util.Toast.ToastUtil;
import com.trans.kuro_core.util.log.KuroLogger;
import com.trans.kuro_core.wechat.KuroWechet;
import com.trans.kuro_core.wechat.callback.IWeChetSignInCallBack;
import com.trans.latte_ec.R;
import com.trans.latte_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInDelegate extends KuroDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail=null;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword=null;

    private boolean isPass=true;
    private ISignListener mISignListener =null;
    @OnClick({
            R2.id.btn_sign_in,
            R2.id.tv_link_sign_up,
            R2.id.icon_sign_in_wechat
    })
    void onClickedView(View view){
        int i=view.getId();
        if (i==R.id.btn_sign_in){
            if (checkForm()){
                RestClient.builder()
                        .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                        .parmas("email", mEmail.getText().toString())
                        .parmas("password", mPassword.getText().toString())
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
//                                ToastUtil.shortShow(response);
                                KuroLogger.json("USER_PROFILE",response);
                                SignHandler.onSignIn(response,mISignListener);
                            }
                        })
                        .build()
                        .post();
            }
        }else if (i==R.id.tv_link_sign_up){
            start(new SignUpDelegate(),SINGLETASK);
        }else if (i==R.id.icon_sign_in_wechat){
            ToastUtil.shortShow("微信登录");
            KuroWechet.getInstance()
                    .onSignSuccess(new IWeChetSignInCallBack() {
                        @Override
                        public void onSignInSuccess(String userInfo) {
                            ToastUtil.shortShow("微信登录成功");
                        }
                    })
                    .signIn();
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ISignListener){
            mISignListener = (ISignListener) context;
        }
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (activity instanceof ISignListener){
//            mISignListener = (ISignListener) activity;
//        }
//    }

    private boolean checkForm(){

        final String email=mEmail.getText().toString();
        final String password=mPassword.getText().toString();


        if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass=false;
        }else {
            mEmail.setError(null);
        }

        if (password.isEmpty()|| password.length()<6){
            mPassword.setError("错误的密码格式");
            isPass=false;
        }else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
