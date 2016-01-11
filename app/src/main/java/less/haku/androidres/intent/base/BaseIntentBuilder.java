package less.haku.androidres.intent.base;

/**
 * Created by HaKu on 16/1/11.
 * IntentBuilder基础类，普通无参数直接跳转类的Activity可以使用此类，
 * 需要传参数的Activity，建议使用子类，可以清晰的显示需要传哪些参数
 */

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

public class BaseIntentBuilder {

    protected Intent intent;

    public BaseIntentBuilder() {

    }

    public BaseIntentBuilder(String uri) {
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    }

    /**
     * 生成Intent
     * @return
     */
    public Intent build() {
        return intent;
    }

    /**
     * 判断intent是否为当前类型 只比较 "type://path"部分，忽略参数
     * @param intent
     * @return
     */
    public boolean sameSchema(Intent intent) {
        String curPath = this.intent.getData().getScheme() + this.intent.getData().getHost();
        String comPath = intent.getData().getScheme() + intent.getData().getHost();
        return curPath.equals(comPath);
    }

    protected String getStringValue(String key) {
        String value = intent.getStringExtra(key);
        if (TextUtils.isEmpty(value)) {
            value = intent.getData().getQueryParameter(key);
        }
        return value;
    }
}
