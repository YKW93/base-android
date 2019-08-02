package com.android.libbase.BaseRecyclerviewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.libbase.BaseRecyclerviewAdapter.listener.BaseRecyclerListener;
import com.android.libbase.BaseRecyclerviewAdapter.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class GenericRVAdapter<T, L extends BaseRecyclerListener, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    private static int ITEM_VIEW = 0;
    private static int ITEM_PROGRESS = 1;

    private List<T> items;
    private L listener;
    private LayoutInflater layoutInflater;
    private OnLoadMoreListener onLoadMoreListener;
    private RecyclerView.LayoutManager layoutManager;

    private boolean isLoading;

    public GenericRVAdapter(Context context, L listener) {
        this.listener = listener;
        layoutInflater = LayoutInflater.from(context);
        items = new ArrayList<>();
    }

    @LayoutRes
    public abstract int getProgressLayout();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_PROGRESS) {
            return new DefaultVH(inflate(getProgressLayout(), viewGroup));
        }
        return onCreateHolder(viewGroup, viewType);
    }

    public abstract VH onCreateHolder(ViewGroup parent, int viewType);

    protected class DefaultVH extends RecyclerView.ViewHolder {

        DefaultVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (items.size() <= position) {
            return;
        }
        T item = items.get(position);

        if (getItemViewType(position) == ITEM_PROGRESS)
            return;

        onBind(holder, item, listener);
    }

    public abstract void onBind(RecyclerView.ViewHolder holder, T item, @Nullable L listener);


    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? ITEM_PROGRESS : ITEM_VIEW;
    }

    public void setItems(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Cannot set 'null' item to the Recycler adapter");
        }
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void addToBeginning(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item to the Recycler adapter");
        }
        items.add(0, item);
        notifyItemInserted(0);
    }


    public void addAll(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Cannot add 'null' items to the Recycler adapter");
        }
        this.items.addAll(items);
        notifyItemRangeChanged(this.items.size() - items.size(), items.size());
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(T item) {
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    /*
    사용 이유 : notifyDataSetChanged 하였을 때 item이 깜빡이는 문제를 해결 하기 위해
    깜박임의 원인 : notifyDataSetChanged를 하였을 때, Adapter가 이미 존재하는 전체 데이터셋의 아이템들 중 어떤 것이 변경된 것인지 모르므로,
    viewHolder를 전부 다시 매칭시켜주면서 발생

    해결 방법
     1. Adapter에게 각 아이템들의 유니크한 값들이 있다고 알려주는것. (setHasStableIds 메소드를 true로 설정 해주면, Adapter가 아이템들을 처적)
     2. 각 아이템의 유니크한 아이디값 부여 (getItemId 메소드를 override 하여서 구현)

     */
    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    public void setListener(L listener) {
        this.listener = listener;
    }

    public L getListener() {
        return listener;
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, @Nullable final ViewGroup parent, final boolean attachToRoot) {
        return layoutInflater.inflate(layout, parent, attachToRoot);
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, final @Nullable ViewGroup parent) {
        return inflate(layout, parent, false);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void endLessScrolled(RecyclerView recyclerView) {
        initRecyclerViewListener(recyclerView);
    }

    private void initRecyclerViewListener(@NonNull RecyclerView recyclerView) {
        this.layoutManager = recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int totalItemCount, visibleTotalCount, lastVisibleItem;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (onLoadMoreListener == null) return;
                if (layoutManager == null) return;

                totalItemCount = layoutManager.getItemCount();
                visibleTotalCount = layoutManager.getChildCount();

                if (totalItemCount <= visibleTotalCount) {
                    return;
                }

                if (layoutManager instanceof GridLayoutManager) {
                    lastVisibleItem = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                }
                else if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }
                else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;

                    int spanCount = staggeredGridLayoutManager.getSpanCount();
                    int[] lastPositions = staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(new int[spanCount]);
                    lastVisibleItem = Math.max(lastPositions[0], lastPositions[1]);
                }

                if (!isLoading && (lastVisibleItem + visibleTotalCount) >= totalItemCount) {
                    onLoadMoreListener.onLoadMore();
                    isLoading = true;
                }
            }
        });
    }

    public void showLoading() {
        this.items.add(null);
        final int index = items.size() - 1;
        this.notifyItemInserted(index);
        this.isLoading = true;
        handledShowProgressViewRow(index);
    }

    // When we use the grid, change span size because show progressView single row
    private void handledShowProgressViewRow(final int index) {
        if (layoutManager != null && layoutManager instanceof GridLayoutManager) {

            GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            final int spanCount = gridLayoutManager.getSpanCount();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isLoading && position == index && items.get(index) == null) {
                        return spanCount;
                    }
                    return 1;
                }
            });
        }
    }

    public void hiddenLoading() {

        if (getItemCount() != 0 && isLoading) {
            this.items.remove(getItemCount() - 1);
            this.notifyItemRemoved(getItemCount());
        }
        isLoading = false;

        if (layoutManager != null && layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
        }

    }


}
