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
import less.haku.androidres.request.base.HOkHttpClient;
import less.haku.androidres.util.JsonUtil;

public class MainActivity extends AppCompatActivity {

    public HKOkHttpClient client = new HKOkHttpClient();
    private HOkHttpClient hClient = new HOkHttpClient();
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

        sendConfigRequest();
    }

    public void sendConfigRequest() {
        ConfigRequest configRequest = new ConfigRequest();

        hClient.sendRequest(configRequest, new HOkHttpClient.IRequestListener<Configuration>() {
            @Override
            public void onSucceed(Configuration configuration) {
                if (configuration == null) {
                    return;
                }
                Log.d("config", configuration.customPhone);
                Log.d("config", configuration.domainName);
            }

            @Override
            public void onFailed(int errCode, String errMsg) {

            }
        });
    }
}
