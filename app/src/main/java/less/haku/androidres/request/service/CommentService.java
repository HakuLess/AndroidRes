package less.haku.androidres.request.service;


import retrofit.Call;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by HaKu on 15/12/30.
 * 一言随机请求
 */
public interface CommentService {
    @GET("/3885454.xml")
    Call<String> getComment();

    @GET("/3885454.xml")
    Observable<String> getCommentRx();

    @GET("/3885454.xml")
    String getCommentSyn();
}
