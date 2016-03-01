package less.haku.androidres.core.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.util.JNIUtil;

/**
 * Created by HaKu on 16/3/1.
 * jni测试
 */
public class JNIActivity extends BaseActivity {

    @Bind(R.id.jni_text)
    TextView jniText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        jniText.setText(JNIUtil.getStringFormC());
    }
}
