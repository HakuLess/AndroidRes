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
                .url("http://10.11.1.26:8081/user-app/api/v1/home?an=com.wjwl.mobile.taocz&appid=210&av=6.5.0&cid=1&h=2560&sid=18047&time=1446435580537&u=a_866002025212761_94368186b1ef31ea_4.4.4_MX4%2BPro_Meizu&w=1536&sign=52FAA3978E1A691D676E4A49F61E7F45")
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
                Log.d("tttttsss", response.body().string() + "\n");
            }
        });
    }
}
