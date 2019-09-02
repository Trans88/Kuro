package com.trans.latte_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.trans.kuro_core.delegates.KuroDelegate;
import com.trans.kuro_core.net.RestClient;
import com.trans.kuro_core.net.callback.ISuccess;
import com.trans.kuro_core.util.log.KuroLogger;
import com.trans.kuro_core.util.Toast.ToastUtil;
import com.trans.latte_ec.R;
import com.trans.latte_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpDelegate extends KuroDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName=null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail=null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone=null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword=null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword=null;

    private boolean isPass=true;
    private ISignListener mISignListener =null;

    @OnClick({
            R2.id.btn_sign_up,
            R2.id.tv_link_sign_in
    })
    void onClickedView(View view){
        int i=view.getId();
        if (i==R.id.btn_sign_up){
            if (checkForm()){
                RestClient.builder()
                        .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                        .parmas("name", mName.getText().toString())
                        .parmas("email", mEmail.getText().toString())
                        .parmas("phone", mPhone.getText().toString())
                        .parmas("password", mPassword.getText().toString())
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
//                                ToastUtil.shortShow(response);
                                KuroLogger.json("USER_PROFILE",response);
                                SignHandler.onSignUp(response,mISignListener);
                            }
                        })
                        .build()
                        .post();
            }
        }else if (i==R.id.tv_link_sign_in){
            start(new SignInDelegate(),SINGLETASK);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    private boolean checkForm(){

        final String name=mName.getText().toString();
        final String email=mEmail.getText().toString();
        final String phone=mPhone.getText().toString();
        final String password=mPassword.getText().toString();
        final String rePassword=mRePassword.getText().toString();

        if (name.isEmpty()){
            mName.setError("请输入姓名");
            isPass=false;
        }else {
            mName.setError(null);
        }

        if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass=false;
        }else {
            mEmail.setError(null);
        }

        if (phone.isEmpty()|| phone.length()!=11){
            mPhone.setError("错误的手机号码格式");
            isPass=false;
        }else {
            mPhone.setError(null);
        }

        if (password.isEmpty()|| password.length()<6){
            mPassword.setError("错误的密码格式");
            isPass=false;
        }else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty()|| rePassword.length()<6||!(rePassword).equals(password)){
            mRePassword.setError("密码验证错误");
            isPass=false;
        }else {
            mRePassword.setError(null);
        }
        return isPass;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
