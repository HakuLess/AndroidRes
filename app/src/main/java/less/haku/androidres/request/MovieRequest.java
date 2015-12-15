package less.haku.androidres.request;

/**
 * Created by HaKu on 15/12/15.
 */

import com.squareup.okhttp.Request;

import less.haku.androidres.data.DoubanBook;
import less.haku.androidres.request.base.BaseRequest;


public class MovieRequest extends BaseRequest {
    //Request的请求参数
    public static class Input {
        public String para1;    //参数1
        public String para2;    //参数2
    }

    public MovieRequest(String key, int size) {
        url = "https://api.douban.com/v2/movie/in_theaters";
        url = addPublicParam(url);
        request = new Request.Builder()
                .url(url)
                .build();

        outCls = DoubanBook.class;
    }
}
