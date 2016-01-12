package less.haku.androidres.request.base;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by HaKu on 15/12/31.
 * Retrofit单例
 */

public class RetrofitSigleton {
    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private RetrofitSigleton(){
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    private volatile static Retrofit retrofit;
    //返回一言API
    public static Retrofit getSingleton() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://api.hitokoto.us")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }

        retrofit.client().interceptors().add(new LoggingInterceptor());
        return retrofit;
    }


    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();

            //可以添加公共参数 增加校验签名等
//            request = request.newBuilder().addHeader("header", "header").build();
            Log.d("retrofit request", request.url().toString());

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            String bodyString = response.body().string();
            Log.d("retrofit response",
                    "request time " + (t2 - t1) / 1e6d + "ms\n" +
                            "request url " + response.request().url().toString() + "\n"
                            + "response body " + bodyString
            );

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();
        }
    }
}
