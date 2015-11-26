package less.haku.androidres.request;

import com.squareup.okhttp.Request;

import less.haku.androidres.data.Book;
import less.haku.androidres.request.base.BaseRequest;

/**
 * Created by HaKu on 15/11/26.
 */
public class BookDetailRequest extends BaseRequest {

    //Request的请求参数
    public static class Input {
        public String para1;    //参数1
        public String para2;    //参数2
    }

    public BookDetailRequest(String id) {
        url = "https://api.douban.com/v2/book/" + id;
        url = addPublicParam(url);
        request = new Request.Builder()
                .url(url)
                .build();

        outCls = Book.class;
    }
}