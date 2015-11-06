package less.haku.androidres.request.base;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import less.haku.androidres.data.Configuration;
import less.haku.androidres.util.JsonUtil;

/**
 * Created by HaKu on 15/11/6.
 */
public class HOkHttpClient{

    private static int SUCCESS_CODE = 200;
    private JSONObject object;
    private OkHttpClient client = new OkHttpClient();

    public HOkHttpClient() {
    }

    public void sendRequest(final BaseRequest baseRequest, final IRequestListener listener) {
        Log.d("send request", baseRequest.request.urlString());
        client.newCall(baseRequest.request).enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    object = new JSONObject(response.body().string());
                } catch (JSONException e) {

                }
                int code = object.optInt("code");
                String message = object.optString("message");
                if (code == SUCCESS_CODE) {
                    //请求成功，进行具体处理
                    Log.d("request success", object.toString());
                    String obj = object.optString("result");

                    if (obj != null) {
                        listener.onSucceed(JsonUtil.json2Java(obj, baseRequest.outCls));
                    }
                } else {
                    //请求错误，进行具体处理
                    Log.d("request error", object.toString());
                    listener.onFailed(code, message);
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                    Log.d("request failed", request.urlString());
            }
        });
    }

    public interface IRequestListener<T> {
        void onSucceed(T response);
        void onFailed(int errCode, String errMsg);
    }
}
