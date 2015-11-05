package less.haku.androidres.request.base;

import org.json.JSONObject;

/**
 * Created by HaKu on 15/11/5.
 */
import android.content.Context;
import java.util.HashMap;

public class BaseRequest {
    protected int method;
    protected String url;
    protected String encryptUrl;
    protected JSONObject jsonObj = new JSONObject();
    protected HashMap<String, String> paramsMap;
    protected HashMap<String, String> headers;
    protected Context context;
    protected int successCode;
    protected int retry = 2;
    protected int timeout = 5000;
    protected Class<?> outCls;

    public BaseRequest(Context context) {
        this.context = context;
        this.headers = new HashMap();
        this.headers.put("Charset", "UTF-8");
    }

    protected void setOutClass(Class<?> outCls) {
        this.outCls = outCls;
    }

    protected HashMap<String, String> getPublicParam() {
        return null;
    }

    protected String encryptUrl(String url) {
        return url;
    }
}
