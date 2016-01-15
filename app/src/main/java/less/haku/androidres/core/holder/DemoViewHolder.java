package less.haku.androidres.core.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import less.haku.androidres.R;

/**
 * Created by HaKu on 16/1/14.
 * RecyclerViewDemo用Holder
 */
public class DemoViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.item_recycler_image)
    public ImageView itemImage;

    @Bind(R.id.item_recycler_text)
    public TextView itemText;

    public DemoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}