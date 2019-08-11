package com.android.baseApp;

import android.widget.ImageView;

import com.android.libbase.recyclerview.RVModel;
import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class AdapterModel implements RVModel {

    private String title;
    private String subTitle;
    private String imageUrl;

    public AdapterModel(String title, String subTitle, String imageUrl) {
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @BindingAdapter({"setImage"})
    public static void setImageUrl(ImageView imageView, String url) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(imageView.getContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

        Glide.with(imageView.getContext()).load(url).thumbnail(0.1f).placeholder(circularProgressDrawable).error(R.drawable.ic_home).into(imageView);
    }
}
