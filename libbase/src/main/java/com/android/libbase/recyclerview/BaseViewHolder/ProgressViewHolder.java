package com.android.libbase.recyclerview.BaseViewHolder;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class ProgressViewHolder extends RecyclerView.ViewHolder {

    ViewDataBinding progressBinding;

    public ProgressViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
        this.progressBinding = binding;
    }
}
