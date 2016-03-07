package less.haku.androidres.core.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;

/**
 * Created by HaKu on 16/3/7.
 * 测试launchMode
 */
public class LaunchModeActivity extends BaseActivity {

    @Bind(R.id.launch_mode_log)
    TextView logView;

    private ActivityManager activityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        ButterKnife.bind(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE) ;
        init();
    }

    private void init() {
        String log = "";
        List<ActivityManager.RunningTaskInfo> appList = activityManager.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo running : appList) {
            log += running.baseActivity.getClassName() + "\n";
        }
        logView.setText(log);
    }

    @OnClick(R.id.launch_mode_standard)
    void openStandard() {
        Intent intent = new Intent(this, LaunchModeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.launch_mode_singleTop)
    void openSingleTop() {
        Intent intent = new Intent(this, LaunchModeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.launch_mode_singleTask)
    void openSingleTask() {
        Intent intent = new Intent(this, LaunchModeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.launch_mode_singleInstance)
    void openSingleInstance() {
        Intent intent = new Intent(this, LaunchModeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
