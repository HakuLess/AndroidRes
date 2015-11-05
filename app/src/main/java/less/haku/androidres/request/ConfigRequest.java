package less.haku.androidres.request;

import com.squareup.okhttp.Request;

/**
 * Created by HaKu on 15/11/5.
 */
public class ConfigRequest {
    public Request request;

    public ConfigRequest() {
        request = new Request.Builder()
                .url("http://user.app.loukou.com/user-app/api/v1/user/configure?an=HKApp111")
                .build();
    }
}
