package com.android.baseApp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.android.baseApp.baseRecyclerviewAdapter.listener.OnLoadMoreListener;
import com.android.baseApp.databinding.ActivityMainBinding;
import com.android.libbase.recyclerview.RVModel;
import com.android.libbase.ui.base.BaseActivity;
import com.hugocastelani.waterfalltoolbar.Dp;

import java.time.chrono.MinguoChronology;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    ActivityMainBinding mainBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mainBinding = getViewDataBinding();
        mainBinding.setActivityMain(this);
        setToolbar();
        setRecyclerView();
    }

    private void setToolbar() {
        mainBinding.setToolbar(getToolbarConfiguration());
        getToolbarConfiguration().setConfiguration("툴바 제목", R.drawable.ic_home, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "클릭", Toast.LENGTH_SHORT).show();
            }
        });

        mainBinding.toolbarLayout.waterfallToolbar.setRecyclerView(mainBinding.recyclerview);
        mainBinding.toolbarLayout.waterfallToolbar.setFinalElevation(new Dp(10).toPx());
        mainBinding.toolbarLayout.waterfallToolbar.setScrollFinalPosition(4);

    }

    private void setRecyclerView() {

        mainBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        GenericRVAdapterTest adapter = new GenericRVAdapterTest(getBaseContext(), null);
        adapter.addAll(getItems());
        mainBinding.recyclerview.setAdapter(adapter);

        mainBinding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private List<RVModel> getItems() {
        return Arrays.asList(new AdapterModel("홍길동", "32", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("김철수", "50", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("안길영", "15", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("Ailee","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("asd","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("314","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("adsvv","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("cccc","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("qqqq","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("rr","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("ee","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"),
                new AdapterModel("34f33","25", "https://t1.daumcdn.net/cfile/tistory/194599374F7049A901"));
    }

}
