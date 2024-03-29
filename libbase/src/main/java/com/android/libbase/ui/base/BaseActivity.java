package com.android.libbase.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.android.libbase.databinding.configurations.ToolbarConfiguration;
import com.android.libbase.utils.CommonUtils;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private ToolbarConfiguration toolbarConfiguration = new ToolbarConfiguration();

    @LayoutRes
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initView();
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    protected ToolbarConfiguration getToolbarConfiguration() {
        return toolbarConfiguration;
    }

    protected abstract void initView();
}
