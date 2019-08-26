package com.android.libbase.databinding;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BaseBindingAdapter {
    @BindingAdapter({"imgRes"})
    public static void imageLoad(ImageView imageView, Integer resId) {
        if (resId != null) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(resId);
        }
        else
            imageView.setVisibility(View.GONE);
    }

    @BindingAdapter({"navigationOnClickListener"})
    public static void setClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }
}
