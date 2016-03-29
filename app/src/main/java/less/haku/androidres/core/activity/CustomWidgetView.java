package less.haku.androidres.core.activity;

import android.os.Bundle;
import android.os.CountDownTimer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.widget.CircleProgress;

/**
 * Created by HaKu on 16/3/29.
 * 自定义控件展示页
 */
public class CustomWidgetView extends BaseActivity {
    @Bind(R.id.custom_circle_progress)
    CircleProgress circleProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_widget);
        ButterKnife.bind(this);

        init();
    }

    @OnClick(R.id.custom_circle_progress)
    void setCircleProgress() {
        int progress = circleProgress.getCurrentProgress();
        circleProgress.setCurrentProgress(progress + 10);
    }

    private void init() {
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                circleProgress.setCurrentProgress((int)millisUntilFinished / 100);
            }
            public void onFinish() {
                circleProgress.setCurrentProgress(0);
            }
        }.start();
    }
}
