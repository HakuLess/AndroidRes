package less.haku.androidres.request;

import com.squareup.okhttp.Request;

import less.haku.androidres.data.Configuration;
import less.haku.androidres.request.base.BaseRequest;

/**
 * Created by HaKu on 15/11/5.
 */
public class ConfigRequest extends BaseRequest{

    //Request的请求参数
    public static class Input {
        public String para1;    //参数1
        public String para2;    //参数2
    }

    public ConfigRequest() {
        url = "http://user.app.loukou.com/user-app/api/v1/user/configure?an=HKApp111";
        url = addPublicParam(url);
        request = new Request.Builder()
                .url(url)
                .build();

        outCls = Configuration.class;
    }
}
