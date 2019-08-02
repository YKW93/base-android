package com.android.libbase.BaseRecyclerviewAdapter.listener;

public interface OnRecyclerObjectClickListener<T> extends BaseRecyclerListener {
    void onItemClicked(T item);
}
