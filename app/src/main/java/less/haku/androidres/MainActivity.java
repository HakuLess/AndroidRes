package less.haku.androidres;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import less.haku.androidres.data.HttpCode;
import less.haku.androidres.util.JsonUtil;

public class MainActivity extends AppCompatActivity {

    public OkHttpClient client = new OkHttpClient();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) this.findViewById(R.id.main_image);
        Glide.with(this)
//                .load("http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif")
                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .into(imageView);

        try {
            run();
        } catch (Exception e) {

        }
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://api.bilibili.cn/list?type=json&tid=33&page=1&pagesize=10&order=default")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.d("ttttt", responseHeaders.name(i) + ": " + responseHeaders.value(i) + "\n");
                }
                HttpCode o = JsonUtil.json2Java(response.body().string(), HttpCode.class);
                Log.d("tttttsss", response.body().string() + "\n");
                Log.d("tttttsss", o.code + " " + o.error  + "\n");
            }
        });
    }
}
