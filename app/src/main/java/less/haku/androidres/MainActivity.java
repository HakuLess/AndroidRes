package less.haku.androidres;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import less.haku.androidres.data.Configuration;
import less.haku.androidres.request.ConfigRequest;
import less.haku.androidres.request.base.HKCallBack;
import less.haku.androidres.request.base.HKOkHttpClient;
import less.haku.androidres.util.JsonUtil;

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
        ConfigRequest configRequest = new ConfigRequest();

        client.newCall(configRequest.request).enqueue(new HKCallBack(
                new HKCallBack.IRequestListener<Object>() {
                    @Override
                    public void onSucceed(Object response) {
                        Configuration configuration;
                        configuration = JsonUtil.json2Java(response.toString(), Configuration.class);
                        if (configuration == null) {
                            return;
                        }
                        Log.d("config", configuration.customPhone);
                    }

                    @Override
                    public void onFailed(int errCode, String message) {

                    }
                }
        ) {
        });
    }
}
