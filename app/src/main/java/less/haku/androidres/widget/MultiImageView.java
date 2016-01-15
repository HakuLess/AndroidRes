package less.haku.androidres.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import less.haku.androidres.application.HApplication;

/**
 * Created by HaKu on 16/1/15.
 * 多重第三方加载ImageView
 */
public class MultiImageView extends ImageView {
    public static final int TYPE_GLIDE = 0;
    public static final int TYPE_PICASSO = 1;

    private int type = 0;   //默认使用Glide加载图片
    private Context context;
    public MultiImageView(Context context) {
        super(context);
        this.context = context;
    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MultiImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public MultiImageView type(int type) {
        this.type = type;
        return this;
    }
    /**
     * 通过不同图片加载库加载网络图片
     * */
    public void setUrl(String url) {
        switch (type) {
            case TYPE_GLIDE :
                Glide.with(HApplication.instance()).load(url).into(this);
                Log.d("Glide image", "image Url " + url);
                break;
            case TYPE_PICASSO :
                Picasso.with(HApplication.instance()).load(url).into(this);
                Log.d("Picasso image", "image Url " + url);
                break;
            default:
                Log.e("MultiImageView", "加载未知类型");
        }
    }
}
