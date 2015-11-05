package less.haku.androidres.request.base;

import android.util.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by HaKu on 15/11/5.
 */
public class HKCallBack implements Callback {
    private static int SUCCESS_CODE = 200;
    private JSONObject object;
    @Override
    public void onResponse(Response response) throws IOException {
        try {
            object = new JSONObject(response.body().string());
        } catch (JSONException e) {

        }
        int code = object.optInt("code");
        if (code == SUCCESS_CODE) {
            //请求成功，进行具体处理
            Log.d("request success", object.toString());
        } else {
            //请求错误，进行具体处理
            Log.d("request error", object.toString());
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
        //请求失败，打印日志
        Log.d("request failed", request.urlString());
    }
}
