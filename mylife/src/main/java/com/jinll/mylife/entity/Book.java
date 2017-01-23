package com.jinll.mylife.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * 图书
 */
public class Book implements Parcelable {

    private String id;
    private String name;         //原名
    private String chineseName;  //中文名
    private String author;       //作者
    private String publisher;    //出版社
    private String isbn;
    private String coverUrl;     //封面图片地址
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.chineseName);
        dest.writeString(this.author);
        dest.writeString(this.publisher);
        dest.writeString(this.isbn);
        dest.writeString(this.coverUrl);
        dest.writeLong(this.createTime != null ? this.createTime.getTime() : -1);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.chineseName = in.readString();
        this.author = in.readString();
        this.publisher = in.readString();
        this.isbn = in.readString();
        this.coverUrl = in.readString();
        long tmpCreateTime = in.readLong();
        this.createTime = tmpCreateTime == -1 ? null : new Date(tmpCreateTime);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
