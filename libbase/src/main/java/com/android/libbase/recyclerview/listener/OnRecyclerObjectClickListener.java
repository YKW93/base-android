package com.android.baseApp.baseRecyclerviewAdapter.listener;

public interface OnRecyclerObjectClickListener<T> extends BaseRecyclerListener {
    void onItemClicked(T item);
}
