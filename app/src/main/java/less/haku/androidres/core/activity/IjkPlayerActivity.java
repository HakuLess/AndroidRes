package less.haku.androidres.core.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextPaint;
import android.util.Log;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import less.haku.androidres.R;
import less.haku.androidres.common.BaseActivity;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by HaKu on 16/2/22.
 * ijkplayer测试
 */
public class IjkPlayerActivity extends BaseActivity {

    @Bind(R.id.danmu_view)
    DanmakuView danmakuView;

    private DanmakuContext danmukuContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijkplayer);
        ButterKnife.bind(this);

        initDanmuConfig();
        init();
    }

    private BaseCacheStuffer.Proxy mCacheStufferAdapter = new BaseCacheStuffer.Proxy() {

        @Override
        public void prepareDrawing(final BaseDanmaku danmaku, boolean fromWorkerThread) {
//            if (danmaku.text instanceof Spanned) { // 根据你的条件检查是否需要需要更新弹幕
//            }
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {
            // TODO 重要:清理含有ImageSpan的text中的一些占用内存的资源 例如drawable
            if (danmaku.text instanceof Spanned) {
                danmaku.text = "";
            }
        }
    };

    /**
     * 初始化配置
     */
    private void initDanmuConfig() {
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 2); // 滚动弹幕最大显示2行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        danmukuContext = DanmakuContext.create();
        danmukuContext
                .setDanmakuStyle(IDisplayer.DANMAKU_STYLE_NONE)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)//越大速度越慢
                .setScaleTextSize(1.2f)
//                .setCacheStuffer(new SimpleTextCacheStuffer(), mCacheStufferAdapter)
                .setCacheStuffer(new BackgroundCacheStuffer(), mCacheStufferAdapter)
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);
    }

    private void init() {
        danmakuView.prepare(new BiliDanmukuParser(), danmukuContext);

        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                danmakuView.start();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuView.enableDanmakuDrawingCache(true);
    }

    private int i = 0;
    @OnClick(R.id.danmu_add)
    void addDanmu() {
        i++;
        BaseDanmaku danmaku = danmukuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);

        Log.d("tag", "测试用弹幕 + " + i + "条");
        danmaku.text = "测试用弹幕 + " + i + "条";

        danmaku.padding = 10;
        danmaku.priority = 1;  // 1:一定会显示, 一般用于本机发送的弹幕,但会导致行数的限制失效
        danmaku.isLive = false;
        danmaku.textSize = 20;
        danmaku.textColor = Color.WHITE;
        danmaku.textShadowColor = 0; // 重要：如果有图文混排，最好不要设置描边(设textShadowColor=0)，否则会进行两次复杂的绘制导致运行效率降低
        danmakuView.addDanmaku(danmaku);
    }

    /**
     * 绘制背景(自定义弹幕样式)
     */
    private class BackgroundCacheStuffer extends SpannedCacheStuffer {
        // 通过扩展SimpleTextCacheStuffer或SpannedCacheStuffer个性化你的弹幕样式
        final Paint paint = new Paint();

        @Override
        public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
//            danmaku.padding = 20;  // 在背景绘制模式下增加padding
            super.measure(danmaku, paint, fromWorkerThread);
        }

        @Override
        public void drawBackground(BaseDanmaku danmaku, Canvas canvas, float left, float top) {
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);//黑色 普通

            if (danmaku.isGuest) {//如果是赞 就不要设置背景
                paint.setColor(Color.TRANSPARENT);
            }
            //由于该库并没有提供margin的设置，所以我这边试出这种方法：将danmaku.padding也就是内间距设置大一点，并在这里的RectF中设置绘制弹幕的位置，就可以形成类似margin的效果
            canvas.drawRoundRect(new RectF(left + 1, top + 1
                            , left + danmaku.paintWidth - 1 + 6,
                            top + danmaku.paintHeight - 1 + 6),//+6 主要是底部被截得太厉害了，+6是增加padding的效果
                    1, 1, paint);
        }

        @Override
        public void drawStroke(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, Paint paint) {
            // 禁用描边绘制
        }
    }
}
