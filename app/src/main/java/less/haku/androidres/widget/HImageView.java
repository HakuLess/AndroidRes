package less.haku.androidres.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by HaKu on 16/1/14.
 * 使用Fresco用于图片加载处理
 */
public class HImageView extends SimpleDraweeView {

    public HImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init();
    }

    public HImageView(Context context) {
        super(context);
        init();
    }

    public HImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public HImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        //图片加载进度条
//        this.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
    }

    public void setUrl(String url) {
        Log.d("Fresco image", "image Url " + url);
        this.setImageURI(Uri.parse(url));
    }
}
