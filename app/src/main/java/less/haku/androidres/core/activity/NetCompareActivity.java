package less.haku.androidres.core.activity;

import android.os.Bundle;
import android.util.Log;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.data.Hitokoto;
import less.haku.androidres.request.HitoKotoRequest;
import less.haku.androidres.request.base.HOkHttpClient;
import less.haku.androidres.request.base.RetrofitSigleton;
import less.haku.androidres.request.service.HitokotoService;
import less.haku.androidres.widget.NetCompareItem;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by HaKu on 16/1/11.
 * 网络请求库比较
 */
public class NetCompareActivity extends BaseActivity {

    @Bind(R.id.net_request_retrofit)
    NetCompareItem netRequestRetrofit;

    @Bind(R.id.net_request_okhttp)
    NetCompareItem netRequestOkhttp;

    private Date start;
    private Date end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("网络库比较");
        setContentView(R.layout.activity_net_compare);
        ButterKnife.bind(this);
        init();
//        HitokotoRequest();
    }

    /**
     * 通过接口初始化按钮点击事件
     */
    public void init() {
        netRequestOkhttp.setRequestListener(new NetCompareItem.RequestListener() {
            @Override
            public void send() {
                HitokotoRequest();
            }
        });

        initService();
        netRequestRetrofit.setRequestListener(new NetCompareItem.RequestListener() {
            @Override
            public void send() {
                requestHitokotoByRetrofit();
            }
        });
    }

    /**
     * 一言请求(okhttpClient)
     */
    private void HitokotoRequest() {

        start = new Date(System.currentTimeMillis());

        final HitoKotoRequest request = new HitoKotoRequest();
        sendJsonRequest(request, new HOkHttpClient.IRequestListener<Hitokoto>() {
            @Override
            public void onSucceed(Hitokoto response) {
                end = new Date(System.currentTimeMillis());
                long diff = end.getTime() - start.getTime();

                netRequestOkhttp.response(response.hitokoto)
                        .type("okHttpClient")
                        .requestTime(String.valueOf(diff) + "ms");
            }

            @Override
            public void onFailed(int errCode, String errMsg) {

            }
        });
    }

    private HitokotoService hitokotoService;

    public void initService() {
        hitokotoService = RetrofitSigleton.getSingleton().create(HitokotoService.class);
    }

    public void requestHitokotoByRetrofit() {

        start = new Date(System.currentTimeMillis());
        hitokotoService.getHitoKotoRx()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Hitokoto>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("rxjava error", "hitokotoService");
                    }

                    @Override
                    public void onNext(Hitokoto hitokoto) {
                        end = new Date(System.currentTimeMillis());
                        long diff = end.getTime() - start.getTime();

                        netRequestRetrofit
                                .type("Retrofit with RxJava")
                                .response(hitokoto.hitokoto)
                                .requestTime(String.valueOf(diff) + "ms");
                    }
                });
    }
}
