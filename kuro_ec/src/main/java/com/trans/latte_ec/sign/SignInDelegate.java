package com.trans.latte_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.kuro_core.util.Toast.ToastUtil;
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
    @OnClick({
            R2.id.btn_sign_in,
            R2.id.tv_link_sign_up,
            R2.id.icon_sign_in_wechat
    })
    void onClickedView(View view){
        int i=view.getId();
        if (i==R.id.btn_sign_in){
            ToastUtil.shortShow("登录");
        }else if (i==R.id.tv_link_sign_up){
            start(new SignUpDelegate(),SINGLETASK);
        }else if (i==R.id.icon_sign_in_wechat){
            ToastUtil.shortShow("微信登录");
        }
    }

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
