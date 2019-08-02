package com.android.libbase.BaseActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.android.libbase.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

public abstract class BaseToolbarActivity extends BaseActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.toolbar);
        setToolbar(getToolbarTitle());
    }

    public abstract String getToolbarTitle();

    protected void setToolbar(String title) {
        if (toolbar != null) {
            if (title != null || !title.isEmpty()) {
                toolbar.setTitle(title);
            }
        }
    }

    protected void setToolbarBackButton(Integer image) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null && image != 0) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(image);
            }
        }
    }

    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }

    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
