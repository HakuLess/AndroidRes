package less.haku.androidres.common;

import less.haku.androidres.application.HApplication;

/**
 * Created by HaKu on 16/1/28.
 * 各种管理
 */
public class HakuHelper {
    /**
     * LocationManager 管理定位信息
     * @return LocationManager
     */
    public static LocationManager locationManager() {
        return LocationManager.instance(HApplication.instance());
    }
}