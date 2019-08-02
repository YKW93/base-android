package com.android.libbase;

import android.os.Bundle;

import com.android.libbase.BaseActivity.BaseToolbarActivity;


public class MainActivity extends BaseToolbarActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public String getToolbarTitle() {
        return "hello world";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
