package com.android.libbase;

import android.os.Bundle;

import com.android.libbase.BaseActivity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar("hello");
        setToolbarBackButton(R.drawable.ic_action_back);
    }
}
