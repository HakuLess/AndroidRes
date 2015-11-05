package less.haku.androidres;

import android.app.Application;

/**
 * Created by HaKu on 15/11/5.
 */
public class HKApplication extends Application {

    //全局单例模式覆盖application，onCreate方法为真正应用入口
    private static HKApplication _instance;

    public static HKApplication instance() {
        if (_instance == null) {
            throw new IllegalStateException("Application not init!!!");
        }
        return _instance;
    }

    public HKApplication() {
        _instance = this;
    }
}