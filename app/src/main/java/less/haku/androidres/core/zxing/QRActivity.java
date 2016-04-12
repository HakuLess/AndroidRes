package less.haku.androidres.core.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;

/**
 * Created by HaKu on 16/3/29.
 * 扫描二维码
 */
public class QRActivity extends BaseActivity {
    @Bind(R.id.qr_result)
    TextView qrResult;
    @Bind(R.id.qr_scan)
    TextView qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        qrResult.setText("bbbbbbbbbb");
    }

    @OnClick(R.id.qr_scan)
    void scan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.initiateScan();

//        Intent openCameraIntent = new Intent(QRActivity.this, CaptureActivity.class);
//        startActivityForResult(openCameraIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("SCAN_RESULT");
            if (TextUtils.isEmpty(scanResult)) {
                Log.d("result", "error null");
            } else {
                qrResult.setText(scanResult);
                Log.d("result", scanResult);
            }
        }
    }

}
