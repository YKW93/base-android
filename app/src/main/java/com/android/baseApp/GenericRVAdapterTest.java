package com.android.baseApp;

import android.content.Context;

import com.android.baseApp.databinding.RvItem2Binding;
import com.android.baseApp.databinding.RvItemBinding;
import com.android.libbase.recyclerview.BaseViewHolder.ItemViewHolder;
import com.android.libbase.recyclerview.GenericRVAdapter;
import com.android.libbase.recyclerview.RVModel;
import com.android.libbase.recyclerview.listener.OnRecyclerPositionClickListener;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

public class GenericRVAdapterTest extends GenericRVAdapter<RVModel, OnRecyclerPositionClickListener> {

    private static final int NEW_TYPE = 4;

    GenericRVAdapterTest(Context context, OnRecyclerPositionClickListener listener) {
        super(context, listener);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) != null && position % 3 == 0) {
            return NEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    protected int getItemLayout(int viewType) {
        if (viewType == NEW_TYPE) {
            return R.layout.rv_item2;
        }
        return R.layout.rv_item;
    }

    @Override
    public void onBindView(ViewDataBinding binding, ItemViewHolder viewHolder, RVModel item, int viewType, @Nullable OnRecyclerPositionClickListener listener) {
        AdapterModel model = (AdapterModel) item;

        if (viewType != NEW_TYPE) {
            RvItemBinding rvItemBinding = (RvItemBinding) binding;
            rvItemBinding.setItem(model);
        }
        else {
            RvItem2Binding rvItem2Binding = (RvItem2Binding) binding;
            rvItem2Binding.setItem(model);
        }
    }
}
