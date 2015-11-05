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

import less.haku.androidres.request.base.HKCallBack;
import less.haku.androidres.request.base.HKOkHttpClient;

public class MainActivity extends AppCompatActivity {

    public HKOkHttpClient client = new HKOkHttpClient();
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
                .url("http://user.app.loukou.com/user-app/api/v1/user/configure?an=HKApp111")
//                .url("http://api.bilibili.cn/list?type=json&tid=33&page=1&pagesize=10&order=default")
                .build();

        client.newCall(request).enqueue(new HKCallBack() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                super.onResponse(response);
//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                Headers responseHeaders = response.headers();
//                for (int i = 0; i < responseHeaders.size(); i++) {
//                    Log.d("ttttt", responseHeaders.name(i) + ": " + responseHeaders.value(i) + "\n");
//                }
//                Log.d("tttttsss", response.body().string() + "\n");
//            }
        });
    }
}
