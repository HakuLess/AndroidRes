package less.haku.androidres.core.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.core.adapter.RecyclerViewDemoAdapter;
import less.haku.androidres.core.adapter.TabPagerAdapter;
import less.haku.androidres.data.ViewDemo;

/**
 * Created by HaKu on 16/1/14.
 * 收缩Toolbar使用
 */
public class CollapsingActivity extends BaseActivity {
//    @Bind(R.id.backdrop)
//    ImageView backdrop;
//    @Bind(R.id.collapsing_toolbar)
//    CollapsingToolbarLayout collapsingToolbar;

//    @Bind(R.id.recycler_view)
//    RecyclerView recyclerView;

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
//    @Bind(R.id.collapsing_tab)
//    TabLayout tabLayout;
//    @Bind(R.id.collapsing_viewpager)
//    ViewPager viewPager;
//
    @Bind(R.id.header)
    ImageView header;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapseToolbar;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private List<ViewDemo> viewDemoList;
    private RecyclerViewDemoAdapter demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collapse_toolbar);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        viewDemoList = new ArrayList<>();
        initDemo();

        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    public void initDemo() {
        for (int i = 0; i < 20; i++) {
            ViewDemo viewDemo = new ViewDemo();
            viewDemo.title = "第" + i + "个";
            viewDemoList.add(viewDemo);
        }

        //设置LayoutManager，线性布局
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        demoAdapter = new RecyclerViewDemoAdapter(this);
//        demoAdapter.setDemoList(viewDemoList);
//        recyclerView.setAdapter(demoAdapter);
//
//        demoAdapter.notifyDataSetChanged();
    }
}
