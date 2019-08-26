package com.android.libbase.databinding.configurations;

import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ToolbarConfiguration extends BaseObservable {
    private String title;
    @DrawableRes
    private Integer imageDrawable;
    private View.OnClickListener listener;

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public Integer getImageDrawable() {
        return imageDrawable;
    }

    @Bindable
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setConfiguration(String title, Integer imageDrawable, View.OnClickListener listener) {
        this.title = title;
        this.imageDrawable = imageDrawable;
        this.listener = listener;
        notifyChange();
    }
}
