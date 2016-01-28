package less.haku.androidres.core.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.common.HakuHelper;
import less.haku.androidres.common.LocationManager;

/**
 * Created by HaKu on 16/1/28.
 * 定位功能Activity
 */
public class LocationActivity extends BaseActivity {

    @Bind(R.id.location_btn)
    Button locationBtn;
    @Bind(R.id.location_txt)
    TextView locationTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

    }

    @OnClick(R.id.location_btn)
    void locate() {
        showProgressDialog("正在定位...");
        HakuHelper.locationManager().requestLocation(new LocationManager.ILocationListener() {
            @Override
            public void receiveLocation(BDLocation loc) {
                locationTxt.setText(loc.getAddrStr() +
                         " \ntime:" + loc.getTime());
                dismissDialog();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
