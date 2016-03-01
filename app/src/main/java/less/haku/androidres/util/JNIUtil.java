package less.haku.androidres.util;

/**
 * Created by HaKu on 16/1/11.
 * 测试JNI
 */
public class JNIUtil {
    static {
        System.loadLibrary("JniDemo");
    }
    public static native String getStringFormC();
}
