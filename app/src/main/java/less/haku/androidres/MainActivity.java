package less.haku.androidres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import less.haku.androidres.common.BaseActivity;

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

//        final Intent intent = new Intent(MainActivity.this, DouBanActivity.class);
        final Intent intent = new Intent(MainActivity.this, SurfaceActivity.class);
        //使用统一ImageView
        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        requestConfig();
                        startActivity(intent);
                    }
                }
        );
//        requestConfig();
    }
}
