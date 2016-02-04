package less.haku.androidres.core.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.widget.BadgeView;

/**
 * Created by HaKu on 16/2/4.
 * 数字标注测试Activity
 */
public class BadgeViewActivity extends BaseActivity {

    @Bind(R.id.badge_text)
    TextView badgeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_badge_view);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        BadgeView badgeView = new BadgeView(this);


        badgeView.setTargetView(badgeText);
        badgeView.setTextSize(6.5f);
        badgeView.setBadgeGravity(Gravity.RIGHT);
        badgeView.setBadgeMargin(7, 4, 0, 0);

//        badgeView.setBackgroundColor(Color.BLACK);
        badgeView.setBackground(1000, Color.BLACK);
        badgeView.setBadgeCount(4);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
