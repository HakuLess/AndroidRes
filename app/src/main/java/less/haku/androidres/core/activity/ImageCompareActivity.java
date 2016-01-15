package less.haku.androidres.core.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.widget.HImageView;
import less.haku.androidres.widget.MultiImageView;

/**
 * Created by HaKu on 16/1/11.
 * 图片加载第三方库比较
 */
public class ImageCompareActivity extends BaseActivity {
    @Bind(R.id.image_compare_fresco)
    HImageView imageCompareFresco;
    @Bind(R.id.image_compare_glide)
    MultiImageView imageCompareGlide;
    @Bind(R.id.image_compare_picasso)
    MultiImageView imageComparePicasso;
    @Bind(R.id.image_compare_refresh)
    Button imageCompareRefresh;
    @Bind(R.id.image_compare_edit)
    EditText imageCompareEdit;

    private String imageUrl;
    private List<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("图片库比较");
        setContentView(R.layout.activity_image_compare);
        ButterKnife.bind(this);

        //加载图片
        init();
    }

    private void init() {
        initList();
        setImages();
    }

    private void initList() {
        urlList = new ArrayList<>();
        imageUrl = "http://i2.hdslb.com/u_user/f7a16e4a28fe524ceddfe0860b52d057.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i1.hdslb.com/u_user/b6ed574c94b249d990369d49eebb401b.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i2.hdslb.com/u_user/c44d0e12ef919452e5a13fa3c4f500a8.png";
        urlList.add(imageUrl);

        imageUrl = "http://i2.hdslb.com/u_user/4777b81d798a4ef7db1c4c872a119809.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i1.hdslb.com/u_user/35e040f2aa4288e37390bb1e7592b619.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i2.hdslb.com/u_user/5a23b9fd2e5f0659b8763b413d046ae5.jpg";
        urlList.add(imageUrl);

        imageUrl = "http://i1.hdslb.com/u_user/e8da027adc0a02f3f52a9c8ca45fa7cf.png";
        urlList.add(imageUrl);
    }

    private void setImages() {
        imageUrl = getImageUrl();
        imageCompareFresco.setUrl(imageUrl);
        imageCompareGlide.type(MultiImageView.TYPE_GLIDE).setUrl(imageUrl);
        imageComparePicasso.type(MultiImageView.TYPE_PICASSO).setUrl(imageUrl);
    }

    private String getImageUrl() {
        int n = (int) (Math.random() * urlList.size());
        return urlList.get(n);
//        imageUrl = imageCompareEdit.getText().toString();
//        if (TextUtils.isEmpty(imageUrl)) {
//            return "http://lorempixel.com/400/200/";
//        } else {
//            return "http://i2.hdslb.com/u_user/f7a16e4a28fe524ceddfe0860b52d057.jpg";
//        }
    }

    @OnClick(R.id.image_compare_refresh)
    public void refreshImage() {
        setImages();
    }
}
