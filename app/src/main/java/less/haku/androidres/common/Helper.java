package less.haku.androidres.common;

import android.app.Application;

import less.haku.androidres.HKApplication;
import less.haku.androidres.network.OkHttpManager;

/**
 * Created by HaKu on 15/11/5.
 */
public class Helper {
    /**
     * 获取 DeviceManager，管理硬件相关的信息
     * @return DeviceManager
     */
    public static OkHttpManager okHttpManager() {
        return OkHttpManager.instance(HKApplication.instance());
    }
}
