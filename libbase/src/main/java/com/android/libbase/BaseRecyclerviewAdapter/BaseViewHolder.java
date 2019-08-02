package com.android.libbase.BaseRecyclerviewAdapter;

import android.view.View;

import com.android.libbase.BaseRecyclerviewAdapter.listener.BaseRecyclerListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T, L extends BaseRecyclerListener> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void onBind(T item, @Nullable L listener);
}
