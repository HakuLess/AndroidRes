package less.haku.androidres;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import less.haku.androidres.util.ListAdapter;
import less.haku.androidres.widget.DoubanListItem;

/**
 * Created by HaKu on 15/11/25.
 */
public class DouBanActivity extends BaseActivity {

    private String key;
    private EditText searchEdit;
    private Button searchButton;
    private ListView doubanList;
    private DoubanBook doubanBook;
    private BookListAdapter bookListAdapter;
    private List<Book> bookList;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.douban_activity);

        initViews();
    }

    //初始化UI控件
    private void initViews() {
        searchEdit = (EditText) this.findViewById(R.id.douban_search_key);
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchButton = (Button) this.findViewById(R.id.douban_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行搜索
                searchByKey();
            }
        });

        bookList = new ArrayList<Book>();
        doubanList = (ListView) this.findViewById(R.id.douban_search_list);
        bookListAdapter = new BookListAdapter();
        doubanList.setAdapter(bookListAdapter);
    }

    //改变关键字，清空结果集合以及size
    private void clearData() {
        size = 0;
        bookList.clear();
    }

    /**
     * 通过关键字搜索
     * */
    private void searchByKey() {
        String key = searchEdit.getText().toString();
        final DouBanRequest douBanRequest = new DouBanRequest(key, size);
        sendJsonRequest(douBanRequest, new HOkHttpClient.IRequestListener<DoubanBook>() {
            @Override
            public void onSucceed(DoubanBook doubanBook) {
                if (doubanBook == null) {
                    return;
                }

                if (doubanBook.books == null) {
                    return;
                }
                size += doubanBook.books.size();
                if (doubanBook.books.size() == 0) {
                    isEnd = true;
                } else {
                    isEnd = false;
                }
                bookList.addAll(doubanBook.books);
                bookListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(int errCode, String errMsg) {
                Log.d("error", "on failed");
            }
        });
    }

    private DoubanListItem doubanListItem;
    private boolean isEnd = false;
    /**
     * 豆瓣图书列表适配器
     * */
    class BookListAdapter extends ListAdapter {
        @Override
        public int getCount() {
            if (bookList.size() == 0) {
                return 0;
            }
            return bookList.size() +  + (isEnd ? 0 : 1);
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

            if (position == bookList.size()) {
                searchByKey();
                return getLoadingItem(parent,"加载中");
            } else if (position > bookList.size()) {
                return null;
            }

            if (null == convertView || !(convertView instanceof DoubanListItem)) {
                doubanListItem = new DoubanListItem(DouBanActivity.this);
            } else {
                doubanListItem = (DoubanListItem) convertView;
            }

            doubanListItem.update(bookList.get(position));
            doubanListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(DouBanActivity.this, BookDetailActivity.class);
                    detailIntent.putExtra("id", bookList.get(position).id);
                    startActivity(detailIntent);
//                    showToast("ID号：" + bookList.get(position).id);
                }
            });
            convertView = doubanListItem;
            return convertView;
        }
    }
}
