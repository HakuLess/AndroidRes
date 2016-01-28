package less.haku.androidres.core.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.core.adapter.HomeFunAdapter;
import less.haku.androidres.data.Func;

/**
 * Created by HaKu on 16/1/11.
 * 首页功能分类页
 */
public class HomeActivity extends BaseActivity {
    @Bind(R.id.home_function_list)
    RecyclerView homeFunctionList;

    private HomeFunAdapter homeFunAdapter;  //功能列表Adapter

    private List<Func> funcList;    //功能列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setTitle("功能列表");
        initFuncList();
        init();
    }

    /**
     * 初始化功能列表
     * */
    public void initFuncList() {
        funcList = new ArrayList<>();
        Func func = new Func();
        func.funcName = "网络请求对比";
        func.uri = "haku://netCompare";
        funcList.add(func);

        func = new Func();
        func.funcName = "图片加载对比";
        func.uri = "haku://imageCompare";
        funcList.add(func);

        func = new Func();
        func.funcName = "收缩Toolbar";
        func.uri = "haku://collapsingToolbarLayout";
        funcList.add(func);

        func = new Func();
        func.funcName = "抽屉DrawerLayout";
        func.uri = "haku://drawerlayout";
        funcList.add(func);

        func = new Func();
        func.funcName = "自定义Banner轮播";
        func.uri = "haku://banner";
        funcList.add(func);

        func = new Func();
        func.funcName = "定位功能";
        func.uri = "haku://location";
        funcList.add(func);
    }

    /**
     * 初始化UI
     * */
    public void init () {

        homeFunAdapter = new HomeFunAdapter(this);
        homeFunctionList.setAdapter(homeFunAdapter);

        //设置LayoutManager，线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeFunctionList.setLayoutManager(linearLayoutManager);

        homeFunAdapter.setFuncList(funcList);
        homeFunAdapter.notifyDataSetChanged();
    }
}
