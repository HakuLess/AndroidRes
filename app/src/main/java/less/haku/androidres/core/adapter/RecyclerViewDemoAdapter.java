package less.haku.androidres.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import less.haku.androidres.R;
import less.haku.androidres.core.holder.DemoViewHolder;
import less.haku.androidres.data.ViewDemo;

/**
 * Created by HaKu on 16/1/14.
 * 用于RecyclerView事例Adpater
 */
public class RecyclerViewDemoAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ViewDemo> demoList;

    //构造函数中初始化list
    public RecyclerViewDemoAdapter(Context context) {
        this.context = context;
    }

    public void setDemoList(List<ViewDemo> demoList) {
        this.demoList = demoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_demo, parent, false);

        return new DemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DemoViewHolder) {
            ((DemoViewHolder) holder).itemText.setText(demoList.get(position).title);
            ((DemoViewHolder) holder).itemImage.setImageResource(R.drawable.splash_default);
        }
    }

    @Override
    public int getItemCount() {
        if (demoList != null) {
            return demoList.size();
        } else {
            return 0;
        }
    }
}
