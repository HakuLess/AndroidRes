package less.haku.androidres.network;

import android.app.Application;
import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by HaKu on 15/11/5.
 * okHttp单例
 */
public class OkHttpManager {
    private OkHttpClient okHttpClient;
    private static OkHttpManager _instance;
    private Context context;

    private OkHttpManager(Context context) {
        this.context = context;
    }

    public static OkHttpManager instance(Application app) {
        if (_instance == null) {
            _instance = new OkHttpManager(app);
        }
        return _instance;
    }
}
