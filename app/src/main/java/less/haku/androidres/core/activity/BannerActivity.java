package less.haku.androidres.core.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.widget.banner.Banner;

/**
 * Created by HaKu on 16/1/19.
 * 自定义Banner控件demo
 */
public class BannerActivity extends BaseActivity {

    @Bind(R.id.demo_banner)
    Banner banner;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_banner_demo);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        List<String> url = new ArrayList<>();
        url.add("http://live-shoot.hdslb.net/54746.jpg?1509");
        url.add("http://live-shoot.hdslb.net/55468.jpg?1509");
        url.add("http://live-shoot.hdslb.net/53406.jpg?1509");

        banner.build(url);
        banner.setOnBannerItemClickListener(new Banner.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("onBannerClick", "pos" + position);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        banner.destory();
    }
}
