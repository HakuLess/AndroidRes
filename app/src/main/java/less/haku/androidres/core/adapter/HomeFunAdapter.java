package less.haku.androidres.core.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import less.haku.androidres.R;
import less.haku.androidres.core.holder.FuncViewHolder;
import less.haku.androidres.data.Func;
import less.haku.androidres.intent.base.HIntentFactory;

/**
 * Created by HaKu on 16/1/11.
 * 功能列表Adapter
 */
public class HomeFunAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Func> funcList;

    //构造函数中初始化list
    public HomeFunAdapter(Context context) {
        this.context = context;
    }

    public void setFuncList(List<Func> funcList) {
        this.funcList = funcList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_func_list, parent, false);

        return new FuncViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof FuncViewHolder) {
            ((FuncViewHolder) holder).itemFunText.setText(funcList.get(position).funcName);
            ((FuncViewHolder) holder).itemFunCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = HIntentFactory.goBaseBuilder(funcList.get(position).uri).build();
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (funcList != null) {
            return funcList.size();
        } else {
            return 0;
        }
    }
}
