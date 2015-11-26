package less.haku.androidres;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.data.Book;
import less.haku.androidres.request.BookDetailRequest;
import less.haku.androidres.request.base.HOkHttpClient;

/**
 * Created by HaKu on 15/11/26.
 */
public class BookDetailActivity extends BaseActivity {

    private ImageView imageView;
    private TextView titleTextView;
    private String id;  //书籍ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_activity);

        initViews();
        RequestBookDetails();
    }

    //初始化UI控件
    private void initViews() {
        titleTextView = (TextView) this.findViewById(R.id.book_detail_title);
        imageView = (ImageView) this.findViewById(R.id.book_detail_image);

        id = getIntent().getStringExtra("id");
    }

    /**
     * 请求书籍详情
     * */
    private void RequestBookDetails() {
        final BookDetailRequest bookDetailRequest = new BookDetailRequest(id);
        sendJsonRequest(bookDetailRequest, new HOkHttpClient.IRequestListener<Book>() {
            @Override
            public void onSucceed(Book book) {
                if (book == null) {
                    return;
                }
                update(book);
            }

            @Override
            public void onFailed(int errCode, String errMsg) {
                Log.d("error", "on failed");
            }
        });
    }

    private void update(Book book) {
        Glide.with(this)
                .load(book.image)
                .into(imageView);

        titleTextView.setText(book.title);
    }
}
