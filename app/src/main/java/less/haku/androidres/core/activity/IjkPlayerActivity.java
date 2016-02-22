package less.haku.androidres.core.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;

/**
 * Created by HaKu on 16/2/22.
 * ijkplayer测试
 */
public class IjkPlayerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijkplayer);
        ButterKnife.bind(this);
    }
}
