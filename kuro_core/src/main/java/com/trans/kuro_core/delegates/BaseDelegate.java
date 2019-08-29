package com.trans.kuro_core.delegates;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trans.kuro_core.activities.ProxyActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
/**
*@author TRS透明
*Created on 2019/3/13
*function : 不希望别人或者自己New实例设置成抽象类
*/
public abstract class BaseDelegate extends Fragment implements ISupportFragment {

    private  final SupportFragmentDelegate DELEGATE =new SupportFragmentDelegate(this);

    private Unbinder mUnbinder=null;//butterKnight类型

    protected FragmentActivity _mActivity;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer){
            rootView=inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView= (View) setLayout();
        }else {
            throw new ClassCastException("setLayout() type must be int or View");
        }

        mUnbinder=ButterKnife.bind(this,rootView);
        onBindView(savedInstanceState,rootView);

        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DELEGATE.onAttach((Activity) context);
        _mActivity = DELEGATE.getActivity();
    }
    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public void enqueueAction(Runnable runnable) {
        DELEGATE.enqueueAction(runnable);
    }

    @Override
    public void post(Runnable runnable) {
        DELEGATE.post(runnable);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        DELEGATE.onEnterAnimationEnd(savedInstanceState);
    }

    @Override
    public void onLazyInitView(Bundle savedInstanceState) {
        DELEGATE.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onSupportVisible() {
        DELEGATE.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        DELEGATE.onSupportInvisible();
    }

    @Override
    public boolean isSupportVisible() {
        return DELEGATE.isSupportVisible();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        DELEGATE.setFragmentResult(resultCode, bundle);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        DELEGATE.onFragmentResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewBundle(Bundle args) {
        DELEGATE.onNewBundle(args);
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        DELEGATE.putNewBundle(newBundle);
    }

    @Override
    public boolean onBackPressedSupport() {
         return DELEGATE.onBackPressedSupport();
    }
}
