package less.haku.androidres;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import less.haku.androidres.common.BaseActivity;
import less.haku.androidres.data.Book;
import less.haku.androidres.data.DoubanBook;
import less.haku.androidres.request.DouBanRequest;
import less.haku.androidres.request.base.HOkHttpClient;
import less.haku.androidres.widget.DoubanListItem;

/**
 * Created by HaKu on 15/11/25.
 */
public class DouBanActivity extends BaseActivity {

    private String key;
    private EditText editText;
    private Button searchButton;
    private ListView doubanList;
    private DoubanBook doubanBook;
    private BookListAdapter bookListAdapter;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.douban_activity);

        initViews();
    }

    //初始化UI控件
    private void initViews() {
        editText = (EditText) this.findViewById(R.id.douban_search_key);
        searchButton = (Button) this.findViewById(R.id.douban_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行搜索
                key = editText.getText().toString();
                if (!TextUtils.isEmpty(key)) {
                    searchByKey(key);
                }
            }
        });

        bookList = new ArrayList<Book>();
        doubanList = (ListView) this.findViewById(R.id.douban_search_list);
        bookListAdapter = new BookListAdapter();
        doubanList.setAdapter(bookListAdapter);
    }

    private Handler handler;
    /**
     * 通过关键字搜索
     * */
    private void searchByKey(String key) {

        final DouBanRequest douBanRequest = new DouBanRequest(key);
        sendJsonRequest(douBanRequest, new HOkHttpClient.IRequestListener<DoubanBook>() {
            @Override
            public void onSucceed(DoubanBook doubanBook) {
                showToast("1111111");
                if (doubanBook == null) {
                    return;
                }
                bookList = doubanBook.books;
                showToast(doubanBook.books.size() + "");
                bookListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(int errCode, String errMsg) {
                Log.d("error", "on failed");
            }
        });

//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        //创建一个Request
//        final Request request = new Request.Builder()
//                .url("https://api.douban.com/v2/book/search?q=" + key)
//                .build();
//        //new call
//        Call call = mOkHttpClient.newCall(request);
//        //请求加入调度
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//            }
//
//            @Override
//            public void onResponse(final Response response) throws IOException {
//                final JSONObject object;
//                try {
//                    object = new JSONObject(response.body().string());
//                    doubanBook = JsonUtil.json2Java(object, DoubanBook.class);
//                    //String htmlStr =  response.body().string();
//                    bookList = doubanBook.books;
//                    Log.d("config", doubanBook.books.size() + "");
//                    Log.d("config", doubanBook.start + "");
//
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            showToast(doubanBook.books.size() + "");
//                            bookListAdapter.notifyDataSetChanged();
//                        }
//                    });
//
////                    showToast(doubanBook.books.size() + "");
////                    bookListAdapter.notifyDataSetChanged();
//
//                } catch (JSONException e) {
//
//                }
//
//            }
//        });
    }

    private DoubanListItem doubanListItem;
    /**
     * 豆瓣图书列表适配器
     * */
    class BookListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return bookList.size();
        }

        @Override
        public Object getItem(int position) {
            return bookList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (null == convertView) {
                doubanListItem = new DoubanListItem(DouBanActivity.this);
            } else {
                doubanListItem = (DoubanListItem) convertView;
            }

            doubanListItem.update(bookList.get(position));
            doubanListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("ID号：" + bookList.get(position).id);
                }
            });
            convertView = doubanListItem;
            return convertView;
        }
    }
}
