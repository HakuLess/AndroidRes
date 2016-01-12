package less.haku.androidres.data;

/**
 * Created by HaKu on 16/1/12.
 * 一言实体类
 */
public class Hitokoto {
    public String hitokoto;     //一句话
    /** a Anime - 动画
     b Comic - 漫画
     c Game - 游戏
     d Novel - 小说
     e 原创
     f 来自网络
     g 其他*/
    public String cat;          //出处类型
    public String author;       //投稿人
    public String source;       //出处（可能为空）
    public int like;            //喜欢次数
    public String date;         //投稿日期
    public String catname;      //出处类型名称
    public long id;              //编号

}
