package com.android.libbase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.libbase.BaseRecyclerviewAdapter.GenericRVAdapter;
import com.android.libbase.BaseRecyclerviewAdapter.listener.OnRecyclerObjectClickListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class TestAdapter extends GenericRVAdapter<String, OnRecyclerObjectClickListener<String>, TestAdapter.TestVH> {

    TestAdapter(Context context, OnRecyclerObjectClickListener<String> listener) {
        super(context, listener);
    }

    @Override
    public int getProgressLayout() {
        return R.layout.progress_view;
    }

    @Override
    public TestVH onCreateHolder(ViewGroup parent, int viewType) {
        return new TestVH(inflate(R.layout.rv_item, parent, false));
    }


    @Override
    public void onBind(RecyclerView.ViewHolder holder, final String item, @Nullable final OnRecyclerObjectClickListener<String> listener) {
        final TestVH testVH = (TestVH) holder;
        testVH.tv_title.setText(item);

        if (listener != null) {
            testVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(item);
                }
            });
        }
    }

    class TestVH extends RecyclerView.ViewHolder {

        TextView tv_title;

        TestVH(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
