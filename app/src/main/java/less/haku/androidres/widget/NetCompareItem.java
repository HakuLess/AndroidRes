package less.haku.androidres.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import less.haku.androidres.R;

/**
 * Created by HaKu on 16/1/12.
 * 网络请求比较展示Item
 */
public class NetCompareItem extends LinearLayout {

    @Bind(R.id.net_request_text)
    TextView netRequestText;
    @Bind(R.id.net_request_response)
    TextView netRequestResponse;
    @Bind(R.id.net_request_time)
    TextView netRequestTime;
    @Bind(R.id.net_request_send)
    Button netRequestSend;
    @Bind(R.id.net_request_type)
    TextView netRequestType;

    private RequestListener requestListener;

    public NetCompareItem(Context context) {
        this(context, null);
    }

    public NetCompareItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetCompareItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.item_net_compare, this, true);
        initViews();
        ButterKnife.bind(this);
    }

    private void initViews() {

    }

    public NetCompareItem type(String type) {
        netRequestType.setText(type);
        return this;
    }

    public NetCompareItem response(String result) {
        netRequestResponse.setText(result);
        return this;
    }

    public NetCompareItem requestTime(String time) {
        netRequestTime.setText(time);
        return this;
    }

    public interface RequestListener {
        void send();
    }

    public void setRequestListener(RequestListener listener) {
        this.requestListener = listener;
    }

    @OnClick(R.id.net_request_send)
    public void sendRequest() {
        Log.d("request listener", "send");
        requestListener.send();
    }
}

