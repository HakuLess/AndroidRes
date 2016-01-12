package less.haku.androidres.request;

import com.squareup.okhttp.Request;

import less.haku.androidres.data.Hitokoto;
import less.haku.androidres.request.base.BaseRequest;

/**
 * Created by HaKu on 16/1/12.
 */
public class HitoKotoRequest extends BaseRequest {

    //Request的请求参数
    public static class Input {
        public String para1;    //参数1
        public String para2;    //参数2
    }

    public HitoKotoRequest() {
        url = "http://api.hitokoto.us/rand";
        url = addPublicParam(url);
        request = new Request.Builder()
                .url(url)
                .build();

        outCls = Hitokoto.class;
    }
}