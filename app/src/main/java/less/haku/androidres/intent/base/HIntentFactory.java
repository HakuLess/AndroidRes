package less.haku.androidres.intent.base;

/**
 * Created by HaKu on 16/1/11.
 * 此工厂类主要用来创建IntentBuilder时方便，可以使用代码提醒功能方便生成各个activity对应的Intent.
 * 业务层可以不使用此类，直接通过 new LKxxxIntentBuilder 来创建
 * 修改自 东方大神
 */

public class HIntentFactory {

    /**
     * 通用Intent Builder,一个activity中的Intent不需要带参数时，可以使用此builder
     */
    public static BaseIntentBuilder goBaseBuilder(String baseUri) {
        return new BaseIntentBuilder(baseUri);
    }

    public static BaseIntentBuilder goNetBuilder() {
        return new BaseIntentBuilder("haku://netCompare");
    }


    public static BaseIntentBuilder goImageBuilder() {
        return new BaseIntentBuilder("haku://imageCompare");
    }
}

