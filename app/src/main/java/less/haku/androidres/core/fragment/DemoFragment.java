package less.haku.androidres.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;
import less.haku.androidres.core.adapter.RecyclerViewDemoAdapter;
import less.haku.androidres.data.ViewDemo;

/**
 * Created by HaKu on 16/1/14.
 * 用于展示Demo
 */
public class DemoFragment extends Fragment {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private View rootView;

    private List<ViewDemo> viewDemoList;
    private RecyclerViewDemoAdapter demoAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_demo, container, false);
            ButterKnife.bind(this, rootView);

            init();
        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        ButterKnife.bind(this, rootView);
//        requestIndex();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void init() {
        viewDemoList = new ArrayList<>();
        initDemo();
    }

    public void initDemo() {
        for (int i = 0; i < 20; i++) {
            ViewDemo viewDemo = new ViewDemo();
            viewDemo.title = "第" + i + "个";
            viewDemoList.add(viewDemo);
        }

        //设置LayoutManager，线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        demoAdapter = new RecyclerViewDemoAdapter(this.getContext());
        demoAdapter.setDemoList(viewDemoList);
        recyclerView.setAdapter(demoAdapter);

        demoAdapter.notifyDataSetChanged();
    }
}
