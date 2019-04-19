package com.android.libbase.BaseRecyclerviewAdapter;

public interface OnRecyclerObjectClickListener<T> extends BaseRecyclerListener {
    void onItemClicked(T item);
}
