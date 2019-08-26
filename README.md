# base-android
안드로이드 DataBinding BaseClass 설계

## BaseRecyclerview(using DataBinding) Example

#### Sample Adapter 
- Multi View Type 처리 가능
- Endless scroll (load more)

```Java

public class MyAdapter extends GenericRVAdapter<RVModel, OnRecyclerPositionClickListener> {

    public int NEW_TYPE = 4;

    MyAdapter(Context context, OnRecyclerPositionClickListener listener) {
        super(context, listener);
    }

    @Override
    protected int getProgressLayout() {
        return R.layout.progress_view;
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

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) != null && position % 3 == 0) {
            return NEW_TYPE;
        }
        return super.getItemViewType(position);
    }
}
```

#### MainAcitivy

        AcitivytMainBinding mainBinding = (ActivityMainBinding) getViewDataBinding();
        mainBinding.setActivityMain(this);

        mainBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        GenericRVAdapterTest adapter = new GenericRVAdapterTest(getBaseContext(), null);
        adapter.endLessScrolled(mainBinding.recyclerview);
        adapter.addAll(getItems());
        mainBinding.recyclerview.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                adapter.showLoading();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.hiddenLoading();
                        adapter.addAll(getItems());
                    }
                }, 2500);
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
