package less.haku.androidres.core.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;

/**
 * Created by HaKu on 16/1/11.
 * 功能列表ViewHolder
 */
public class FuncViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_func)
    public CardView itemFunCard;

    @Bind(R.id.item_func_txt)
    public TextView itemFunText;

    public FuncViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}