package less.haku.androidres.request.base;

import android.util.Log;
import android.view.View;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import less.haku.androidres.data.Configuration;
import less.haku.androidres.util.JsonUtil;


/**
 * Created by HaKu on 15/11/5.
 */
public class HKCallBack<T> implements Callback {
    private static int SUCCESS_CODE = 200;
    private JSONObject object;
    protected IRequestListener<Object> listener;

    public HKCallBack(IRequestListener<Object> listener) {
        this.listener = listener;
    }

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
                listener.onSucceed(obj);
            }
        } else {
            //请求错误，进行具体处理
            Log.d("request error", object.toString());
            listener.onFailed(code, message);
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
        //请求失败，打印日志
        Log.d("request failed", request.urlString());
    }

    public interface IRequestListener<T> {
        void onSucceed(T clazz);

        void onFailed(int errCode, String message);
    }
}
