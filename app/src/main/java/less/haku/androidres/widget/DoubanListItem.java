package less.haku.androidres.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import less.haku.androidres.R;
import less.haku.androidres.data.Book;

/**
 * Created by HaKu on 15/11/25.
 */
public class DoubanListItem extends LinearLayout {

    private ImageView imageView;   //图片
    private TextView titleTextView;  //书籍名称
    private String iconUrl;     //图片URL
    private String title;       //名称

    public DoubanListItem(Context context) {
        this(context, null);
    }

    public DoubanListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubanListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.douban_list_item, this, true);
        initViews();
    }

    private void initViews() {
        imageView = (ImageView) this.findViewById(R.id.douban_list_item_image);
        titleTextView = (TextView) this.findViewById(R.id.douban_list_item_title);
    }

    public void update(Book book) {
        Glide.with(getContext())
            .load(book.image)
            .into(imageView);

        titleTextView.setText(book.title);
    }
}
