package less.haku.androidres;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.data.Configuration;
import less.haku.androidres.request.ConfigRequest;
import less.haku.androidres.request.base.HOkHttpClient;

public class MainActivity extends BaseActivity {
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

        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requestConfig();
                    }
                }
        );
        requestConfig();
    }

    public void requestConfig() {

        final ConfigRequest configRequest = new ConfigRequest();

        sendJsonRequest(configRequest, new HOkHttpClient.IRequestListener<Configuration>() {
            @Override
            public void onSucceed(Configuration configuration) {
                if (configuration == null) {
                    return;
                }
                showToast(configuration.domainName);
                Log.d("config", configuration.customPhone);
                Log.d("config", configuration.domainName);
            }

            @Override
            public void onFailed(int errCode, String errMsg) {

            }
        });
    }
}
