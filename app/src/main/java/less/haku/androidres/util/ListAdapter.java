package less.haku.androidres.util;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import less.haku.androidres.R;

/**
 * Created by HaKu on 15/11/26.
 */
public abstract class ListAdapter extends BaseAdapter {

    protected Object LOADING = new Object(); //正在加载
    protected Object RETRY = new Object();   //重试
    protected Object EMPTY = new Object();   //列表为空
    protected Object DATA = new Object();   //正常数据

    public interface IRetry {
        void retry();
    }

    public interface IEmptyClick {
        void onEmptyClick();
    }

    /**
     * 返回一个撑满listview的 empty item
     * @param parent
     * @param message
     * @param resId  小于等于0表示无效ID，使用默认图片
     * @param listener  监听空view点击
     * @return
     */
    protected View getEmptyItem(ViewGroup parent, String message, int resId, final IEmptyClick listener) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_empty, parent, false);
        view.setTag(EMPTY);

        if (resId > 0) {
            ImageView image = (ImageView)view.findViewById(R.id.image);
            image.setImageResource(resId);
        }

        if (!TextUtils.isEmpty(message)) {
            TextView title = (TextView)view.findViewById(R.id.title);
            title.setText(message);
        }

        view.setMinimumHeight(parent.getMeasuredHeight());

        if (listener != null) {
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onEmptyClick();
                }
            });
        }

        return view;
    }

    /**
     * 返回一个表示加载中的item
     * @param parent
     * @param message 可以为空或null
     * @return
     */
    protected View getLoadingItem(ViewGroup parent, String message) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_loading, parent, false);
        view.setTag(LOADING);
        if (!TextUtils.isEmpty(message)) {
            TextView title = (TextView)view.findViewById(R.id.title);
            title.setText(message);
        }
        return view;
    }

    /**
     * 返回一个网络出错item，点击执行retry操作
     * @param parent
     * @param message
     * @param retry
     * @return
     */
    protected View getRetryItem(ViewGroup parent, String message, final IRetry retry) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_retry, parent, false);
        view.setTag(RETRY);
        if (!TextUtils.isEmpty(message)) {
            TextView title = (TextView)view.findViewById(R.id.title);
            title.setText(message);
        }
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (retry != null) {
                    retry.retry();
                }
            }
        });

        return view;
    }

    public boolean isEnabled(int position) {
        Object obj = getItem(position);
        if (obj == RETRY || obj == EMPTY || obj == LOADING) {
            return false;
        }
        return true;
    }

}