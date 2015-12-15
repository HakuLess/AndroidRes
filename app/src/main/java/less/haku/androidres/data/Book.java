package less.haku.androidres.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HaKu on 15/11/25.
 * 豆瓣BOOK部分变量
 */
public class Book implements Parcelable {
    public String image;
    public String id;
    public String url;
    public String title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.id);
        dest.writeString(this.url);
        dest.writeString(this.title);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.image = in.readString();
        this.id = in.readString();
        this.url = in.readString();
        this.title = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
